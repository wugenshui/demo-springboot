package com.github.wugenshui.baseutil.baseutil.controller;

import com.github.wugenshui.baseutil.baseutil.entity.AjaxResult;
import com.github.wugenshui.baseutil.baseutil.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件控制器
 *
 * @author : chenbo
 * @date : 2019-12-08
 */
@RestController
@Slf4j
public class FileUploadController {
    /**
     * 文件上传具体实现方法;
     *
     * @param file 上传文件
     * @return
     */
    @PostMapping("/upload")
    public AjaxResult<String> handleFileUpload(@RequestParam MultipartFile file) {
        AjaxResult<String> result;
        if (!file.isEmpty()) {
            try {
                String path = System.getProperty("user.dir") + "/files/";
                FileUtil.fileupload(file.getBytes(), path, file.getOriginalFilename());
            } catch (IOException e) {
                log.error("文件读取失败", e);
                result = AjaxResult.error("上传失败," + e.getMessage());
            }
            result = AjaxResult.success("上传成功");
        } else {
            result = AjaxResult.error("上传失败，因为文件是空的.");
        }
        return result;
    }
}
