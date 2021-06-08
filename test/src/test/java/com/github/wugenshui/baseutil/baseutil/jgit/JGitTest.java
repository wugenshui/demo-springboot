package com.github.wugenshui.baseutil.baseutil.jgit;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-07-14
 */
@SpringBootTest
@Slf4j
public class JGitTest {
    @Test
    public void test() {
        String path = "./test/chenbo";
        File file = new File(path);
        if (file.exists()) {
            deleteFolder(file);
        }

        try {
            // 克隆仓库
            UsernamePasswordCredentialsProvider provider = new UsernamePasswordCredentialsProvider("账户", "密码");
            CloneCommand cloneCommand = Git.cloneRepository().setURI("http://gitlab/test/cheno.git").setCredentialsProvider(provider);
            cloneCommand.setDirectory(file).call();

            Git git = Git.open(file);

            // 创建新分支
            List<Ref> refs = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
            if (refs.size() < 3) {
                git.branchCreate().setName("release").call();
                git.branchCreate().setName("develop").call();
            }
            // 切换至develop分支
            git.checkout().setName("develop").call();

            // 模拟新增文件
            File tempFile = new File(path + "/2.txt");
            tempFile.createNewFile();

            // 添加
            git.add().addFilepattern(".").call();
            // 提交
            git.commit().setMessage("cicd-init-service自动初始化项目").call();
            // 推送
            git.push().setPushAll().setCredentialsProvider(provider).call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件夹
     *
     * @param file
     */
    private void deleteFolder(File file) {
        try {
            if (file.isFile() || file.list().length == 0) {
                file.delete();
            } else {
                File[] files = file.listFiles();
                for (File getFile : files) {
                    deleteFolder(getFile);
                    getFile.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
