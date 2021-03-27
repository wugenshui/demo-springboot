package com.chenbo.demo.single.best.practice.controller.oss;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
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
    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        InputStream inputStream = file.getInputStream();
        log.info(file.getOriginalFilename() + ':' + file.getContentType());
        ObjectWriteResponse writeResponse = minioClient.putObject(
                PutObjectArgs.builder()
                        .stream(inputStream, file.getSize(), 0)
                        .bucket("file")
                        .contentType(file.getContentType())
                        .object(file.getOriginalFilename())
                        .build()
        );
        return file.getOriginalFilename();
    }

    @ApiOperation("下载附件")
    @GetMapping("/{name}")
    public String download(@PathVariable String name, HttpServletResponse response) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {

        InputStream inputSteam = minioClient.getObject(
                GetObjectArgs.builder().bucket("file")
                        .object(name).build());

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        // response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(name, "UTF-8"));
        byte[] buffer = new byte[1024];
        FileInputStream fis = null; //文件输入流
        BufferedInputStream bis = null;

        OutputStream os = null; //输出流
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(inputSteam);
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer);
                i = bis.read(buffer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
