package com.chenbo.demo.minio;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Tags;
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
public class MinioApplicationTest {
    @Test
    public void test() throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        try {
            MinioClient minioClient = MinioClient
                    .builder()
                    .endpoint("http://192.168.192.168:9000")
                    .credentials("minio", "minio2021")
                    .build();

            Tags bucketTags = minioClient.getBucketTags(GetBucketTagsArgs.builder().bucket("image").build());

            // 桶
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket("image").build());
            if (!isExist) {
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
            System.out.println(image);

            // 获取元数据
            ObjectStat objectStat = minioClient.statObject(StatObjectArgs.builder().bucket("image").object("cat-max.jpg").build());
            System.out.println(objectStat);

            // 获取流数据
            InputStream inputSteam = minioClient.getObject(
                    GetObjectArgs.builder().bucket("image")
                            .object("cat-max.jpg").build());

            // 获取下载地址
            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder().bucket("image").method(Method.GET)
                            .object("cat-max.jpg").expiry(24 * 60 * 60).build());
            System.out.println(url);
        } catch (MinioException ex) {
            System.out.println(ex);
        }
    }
}