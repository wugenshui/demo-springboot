package com.chenbo.demo.single.best.practice.controller.oss;

import com.chenbo.demo.single.best.practice.entity.AjaxResult;
import io.minio.*;
import io.minio.errors.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author : chenbo
 * @date : 2021-03-27
 */
@Api(tags = "文件上传")
@RestController
@RequestMapping("/oss/file")
@Slf4j
public class FileController {
    @Autowired
    private MinioClient minioClient;

    @ApiOperation("上传附件")
    @PostMapping(headers = "content-type=multipart/form-data")
    public AjaxResult<String> upload(@RequestParam("file") MultipartFile file) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        if (file == null || file.isEmpty()) {
            return AjaxResult.error("上传失败，请选择文件");
        }
        InputStream inputStream = file.getInputStream();
        log.info(file.getOriginalFilename() + ':' + file.getContentType());
        minioClient.putObject(
                PutObjectArgs.builder()
                        .stream(inputStream, file.getSize(), 0)
                        .bucket("file")
                        .contentType(file.getContentType())
                        .object(file.getOriginalFilename())
                        .build()
        );
        return AjaxResult.success(file.getOriginalFilename());
    }

    @ApiOperation("下载附件")
    @GetMapping("/{name}")
    public AjaxResult<String> download(@PathVariable String name, HttpServletResponse response) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {

        ObjectStat objectStat = minioClient.statObject(StatObjectArgs.builder().bucket("file").object(name).build());
        System.out.println(objectStat);
        // 读取文件输入流
        InputStream inputSteam = minioClient.getObject(
                GetObjectArgs.builder().bucket("file")
                        .object(name).build());

        response.setContentType(objectStat.contentType());
        response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(name, "UTF-8"));
        byte[] buffer = new byte[1024];

        try (BufferedInputStream bis = new BufferedInputStream(inputSteam);
             OutputStream os = response.getOutputStream()) {
            ;
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("下载失败");
        }

        return AjaxResult.success("下载成功");
    }
}
