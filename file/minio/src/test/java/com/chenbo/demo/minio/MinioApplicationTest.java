package com.chenbo.demo.minio;

import io.minio.BucketExistsArgs;
import io.minio.GetBucketTagsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.MinioException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;
import io.minio.messages.Tags;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author : chenbo
 * @date : 2020-11-20
 */
@SpringBootTest
@Slf4j
public class MinioApplicationTest {
    @Test
    public void test() throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        try {
            MinioClient minioClient = MinioClient
                    .builder()
                    .endpoint("http://192.168.0.222:9000")
                    .credentials("minioadmin", "minioadmin")
                    .build();

            Tags bucketTags = minioClient.getBucketTags(GetBucketTagsArgs.builder().bucket("image").build());

            // 桶
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("image").build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("image").build());
            } else {
                System.out.println("Bucket already exists.");
            }

            // 上传
            minioClient.uploadObject(UploadObjectArgs.builder()
                    .bucket("image")
                    .object("cat-max.jpg")
                    .filename("src/test/resources/cat.jpg")
                    .build());

            String image = minioClient.getObjectUrl("image", "cat-max.jpg");
            log.info(image);

            InputStream inputSteam = minioClient.getObject(
                    GetObjectArgs.builder().bucket("image")
                            .object("cat-max.jpg").build());

            // 获取下载地址
            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder().bucket("image").method(Method.GET)
                            .object("cat-max.jpg").expiry(24 * 60 * 60).build());
            log.info(url);
        } catch (MinioException ex) {
            log.error("minio error", ex);
        }
    }
}