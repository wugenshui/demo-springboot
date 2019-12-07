package com.chenbo.baseutil.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件工具类
 *
 * @author : chenbo
 * @date : 2019-12-08
 */
public class FileUtil {
    private FileUtil() {
        throw new IllegalStateException("工具类无需实例化！");
    }

    /**
     * 在指定目录下添加指定文件
     *
     * @param file      文件的二进制
     * @param directory 文件路径
     * @param fileName  文件名
     * @throws IOException
     */
    public static void fileupload(byte[] file, String directory, String fileName) throws IOException {
        //目标目录
        File targetDirectory = new File(directory);
        if (!targetDirectory.exists()) {
            targetDirectory.mkdirs();
        }

        //二进制流写入
        try (FileOutputStream fileOutputStream = new FileOutputStream(directory + fileName)) {
            fileOutputStream.write(file);
            fileOutputStream.flush();
        } catch (Exception e) {
            throw e;
        }
    }
}
