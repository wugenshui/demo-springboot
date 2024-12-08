package com.wugenshui.github.code.quick;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.io.File.separator;

/**
 * @author : chenbo
 * @date : 2024-07-14
 */
@Slf4j
public class App {
    private static final Map<String, String> maps = new HashMap<>();
    /**
     * 白名单文件，不进行重写，避免出问题
     */
    private static final Set<String> WHITE_FILE_TYPES = CollUtil.newHashSet("gif", "jpg", "svg", "png", // 图片
            "eot", "woff2", "ttf", "woff",  // 字体
            "xdb"); // IP 库

    public static void main(String[] args) {
        //maps.put("root", "123456");
        maps.put("t\\('common.copy'\\)", "'复制'");
        maps.put("t\\('sys.api.operationFailed'\\)", "'操作失败'");maps.put("t\\('sys.api.errorTip'\\)", "'错误提示'");maps.put("t\\('sys.api.errorMessage'\\)", "'操作失败,系统异常!'");maps.put("t\\('sys.api.timeoutMessage'\\)", "'登录超时,请重新登录!'");maps.put("t\\('sys.api.apiTimeoutMessage'\\)", "'接口请求超时,请刷新页面重试!'");maps.put("t\\('sys.api.apiRequestFailed'\\)", "'请求出错，请稍候重试'");maps.put("t\\('sys.api.networkException'\\)", "'网络异常'");maps.put("t\\('sys.api.networkExceptionMsg'\\)", "'网络异常，请检查您的网络连接是否正常!'");maps.put("t\\('sys.api.errMsg401'\\)", "'用户没有权限（令牌、用户名、密码错误）!'");maps.put("t\\('sys.api.errMsg403'\\)", "'用户得到授权，但是访问是被禁止的。!'");maps.put("t\\('sys.api.errMsg404'\\)", "'网络请求错误,未找到该资源!'");maps.put("t\\('sys.api.errMsg405'\\)", "'网络请求错误,请求方法未允许!'");maps.put("t\\('sys.api.errMsg408'\\)", "'网络请求超时!'");maps.put("t\\('sys.api.errMsg500'\\)", "'服务器错误,请联系管理员!'");maps.put("t\\('sys.api.errMsg501'\\)", "'网络未实现!'");maps.put("t\\('sys.api.errMsg502'\\)", "'网络错误!'");maps.put("t\\('sys.api.errMsg503'\\)", "'服务不可用，服务器暂时过载或维护!'");maps.put("t\\('sys.api.errMsg504'\\)", "'网络超时!'");maps.put("t\\('sys.api.errMsg505'\\)", "'http版本不支持该请求!'");maps.put("t\\('sys.api.errMsg901'\\)", "'演示模式，无法进行写操作!'");maps.put("t\\('sys.app.logoutTip'\\)", "'温馨提醒'");maps.put("t\\('sys.app.logoutMessage'\\)", "'是否确认退出系统?'");maps.put("t\\('sys.app.menuLoading'\\)", "'菜单加载中...'");maps.put("t\\('sys.exception.backLogin'\\)", "'返回登录'");maps.put("t\\('sys.exception.backHome'\\)", "'返回首页'");maps.put("t\\('sys.exception.subTitle403'\\)", "'抱歉，您无权访问此页面。'");maps.put("t\\('sys.exception.subTitle404'\\)", "'抱歉，您访问的页面不存在。'");maps.put("t\\('sys.exception.subTitle500'\\)", "'抱歉，服务器报告错误。'");maps.put("t\\('sys.exception.noDataTitle'\\)", "'当前页无数据'");maps.put("t\\('sys.exception.networkErrorTitle'\\)", "'网络错误'");maps.put("t\\('sys.exception.networkErrorSubTitle'\\)", "'抱歉，您的网络连接已断开，请检查您的网络！'");maps.put("t\\('sys.lock.unlock'\\)", "'点击解锁'");maps.put("t\\('sys.lock.alert'\\)", "'锁屏密码错误'");maps.put("t\\('sys.lock.backToLogin'\\)", "'返回登录'");maps.put("t\\('sys.lock.entry'\\)", "'进入系统'");maps.put("t\\('sys.lock.placeholder'\\)", "'请输入锁屏密码或者用户密码'");maps.put("t\\('sys.login.backSignIn'\\)", "'返回'");maps.put("t\\('sys.login.signInFormTitle'\\)", "'登录'");maps.put("t\\('sys.login.ssoFormTitle'\\)", "'三方授权'");maps.put("t\\('sys.login.mobileSignInFormTitle'\\)", "'手机登录'");maps.put("t\\('sys.login.qrSignInFormTitle'\\)", "'二维码登录'");maps.put("t\\('sys.login.signUpFormTitle'\\)", "'注册'");maps.put("t\\('sys.login.forgetFormTitle'\\)", "'重置密码'");maps.put("t\\('sys.login.signInTitle'\\)", "'开箱即用的中后台管理系统'");maps.put("t\\('sys.login.signInDesc'\\)", "'输入您的个人详细信息开始使用！'");maps.put("t\\('sys.login.policy'\\)", "'我同意xxx隐私政策'");maps.put("t\\('sys.login.loginButton'\\)", "'登录'");maps.put("t\\('sys.login.registerButton'\\)", "'注册'");maps.put("t\\('sys.login.rememberMe'\\)", "'记住我'");maps.put("t\\('sys.login.forgetPassword'\\)", "'忘记密码?'");maps.put("t\\('sys.login.otherSignIn'\\)", "'其他登录方式'");maps.put("t\\('sys.login.loginSuccessTitle'\\)", "'登录成功'");maps.put("t\\('sys.login.loginSuccessDesc'\\)", "'欢迎回来'");maps.put("t\\('sys.login.accountPlaceholder'\\)", "'请输入账号'");maps.put("t\\('sys.login.passwordPlaceholder'\\)", "'请输入密码'");maps.put("t\\('sys.login.smsPlaceholder'\\)", "'请输入验证码'");maps.put("t\\('sys.login.mobilePlaceholder'\\)", "'请输入手机号码'");maps.put("t\\('sys.login.policyPlaceholder'\\)", "'勾选后才能注册'");maps.put("t\\('sys.login.diffPwd'\\)", "'两次输入密码不一致'");maps.put("t\\('sys.login.userName'\\)", "'账号'");maps.put("t\\('sys.login.password'\\)", "'密码'");maps.put("t\\('sys.login.confirmPassword'\\)", "'确认密码'");maps.put("t\\('sys.login.email'\\)", "'邮箱'");maps.put("t\\('sys.login.smsCode'\\)", "'短信验证码'");maps.put("t\\('sys.login.mobile'\\)", "'手机号码'");maps.put("t\\('profile.user.title'\\)", "'个人信息'");maps.put("t\\('profile.user.username'\\)", "'用户名称'");maps.put("t\\('profile.user.nickname'\\)", "'用户昵称'");maps.put("t\\('profile.user.mobile'\\)", "'手机号码'");maps.put("t\\('profile.user.email'\\)", "'用户邮箱'");maps.put("t\\('profile.user.dept'\\)", "'所属部门'");maps.put("t\\('profile.user.posts'\\)", "'所属岗位'");maps.put("t\\('profile.user.roles'\\)", "'所属角色'");maps.put("t\\('profile.user.sex'\\)", "'性别'");maps.put("t\\('profile.user.man'\\)", "'男'");maps.put("t\\('profile.user.woman'\\)", "'女'");maps.put("t\\('profile.user.createTime'\\)", "'创建日期'");maps.put("t\\('profile.info.title'\\)", "'基本信息'");maps.put("t\\('profile.info.basicInfo'\\)", "'基本资料'");maps.put("t\\('profile.info.resetPwd'\\)", "'修改密码'");maps.put("t\\('profile.info.userSocial'\\)", "'社交信息'");maps.put("t\\('profile.rules.nickname'\\)", "'请输入用户昵称'");maps.put("t\\('profile.rules.mail'\\)", "'请输入邮箱地址'");maps.put("t\\('profile.rules.truemail'\\)", "'请输入正确的邮箱地址'");maps.put("t\\('profile.rules.phone'\\)", "'请输入正确的手机号码'");maps.put("t\\('profile.rules.truephone'\\)", "'请输入正确的手机号码'");maps.put("t\\('profile.password.oldPassword'\\)", "'旧密码'");maps.put("t\\('profile.password.newPassword'\\)", "'新密码'");maps.put("t\\('profile.password.confirmPassword'\\)", "'确认密码'");maps.put("t\\('profile.password.oldPwdMsg'\\)", "'请输入旧密码'");maps.put("t\\('profile.password.newPwdMsg'\\)", "'请输入新密码'");maps.put("t\\('profile.password.cfPwdMsg'\\)", "'请输入确认密码'");maps.put("t\\('profile.password.pwdRules'\\)", "'长度在 6 到 20 个字符'");maps.put("t\\('profile.password.diffPwd'\\)", "'两次输入密码不一致'");
        long start = System.currentTimeMillis();
        String projectBaseDir = "D:\\company\\jdi2\\jdi2-web";
        log.info("[main][原项目路径改地址 ({})]", projectBaseDir);

        Collection<File> files = listFiles(projectBaseDir);
        // 写入文件
        files.forEach(file -> {
            // 如果是白名单的文件类型，不进行重写，直接拷贝
            String fileType = getFileType(file);
            if (WHITE_FILE_TYPES.contains(fileType)) {
                return;
            }
            // 如果非白名单的文件类型，重写内容，在生成文件
            replaceFileContent(file, projectBaseDir);
        });
        log.info("[main][重写完成]共耗时：{} 秒", (System.currentTimeMillis() - start) / 1000);
    }

    private static Collection<File> listFiles(String projectBaseDir) {
        Collection<File> files = FileUtil.loopFiles(projectBaseDir);
        // 移除 IDEA、Git 自身的文件、Node 编译出来的文件
        files = files.stream()
                .filter(file -> !file.getPath().contains(separator + "target" + separator)
                        && !file.getPath().contains(separator + "node_modules" + separator)
                        && !file.getPath().contains(separator + ".idea" + separator)
                        && !file.getPath().contains(separator + ".git" + separator)
                        && !file.getPath().contains(separator + ".vocode" + separator)
                        && !file.getPath().contains(separator + "dist" + separator)
                        && !file.getPath().contains(".iml")
                        && !file.getPath().contains(".html.gz"))
                .collect(Collectors.toList());
        return files;
    }

    private static void replaceFileContent(File file, String projectBaseDir) {
        String content = FileUtil.readString(file, StandardCharsets.UTF_8);
        // 如果是白名单的文件类型，不进行重写
        String fileType = getFileType(file);
        if (WHITE_FILE_TYPES.contains(fileType)) {
            return;
        }
        //log.info(content);
        String content1 = new String(content);
        //log.info(content1);
        // 执行文件内容都重写
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            content1 = content1.replaceAll(entry.getKey(), entry.getValue());
        }
        if (!StrUtil.equals(content, content1)) {
            writeFile(file, content1, projectBaseDir, file.getAbsolutePath());
            log.info("change file:" + file.getAbsolutePath());
        }
    }

    private static void writeFile(File file, String fileContent, String projectBaseDir,
                                  String projectBaseDirNew) {
        FileUtil.writeUtf8String(fileContent, projectBaseDirNew);
    }

    private static String getFileType(File file) {
        return file.length() > 0 ? FileTypeUtil.getType(file) : "";
    }
}
