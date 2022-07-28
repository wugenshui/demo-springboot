package com.github.wugenshui.minio;

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
                    .endpoint("http://192.168.0.222:9000")
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

            System.out.println(defaultBucketPolicy("image"));
            // 设置桶访问策略
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket("image").config(defaultBucketPolicy("image").toString()).build());

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

    /**
     * 获取默认桶策略,可以读写
     *
     * @param bucketName 桶名称
     * @return
     */
    private static StringBuilder defaultBucketPolicy(String bucketName) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n" +
                "    \"Version\": \"2012-10-17\",\n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Effect\": \"Allow\",\n" +
                "            \"Principal\": {\n" +
                "                \"AWS\": [\n" +
                "                    \"*\"\n" +
                "                ]\n" +
                "            },\n" +
                "            \"Action\": [\n" +
                "                \"s3:GetBucketLocation\"\n" +
                "            ],\n" +
                "            \"Resource\": [\n" +
                "                \"arn:aws:s3:::" + bucketName + "\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"Effect\": \"Allow\",\n" +
                "            \"Principal\": {\n" +
                "                \"AWS\": [\n" +
                "                    \"*\"\n" +
                "                ]\n" +
                "            },\n" +
                "            \"Action\": [\n" +
                "                \"s3:ListBucket\"\n" +
                "            ],\n" +
                "            \"Resource\": [\n" +
                "                \"arn:aws:s3:::" + bucketName + "\"\n" +
                "            ],\n" +
                "            \"Condition\": {\n" +
                "                \"StringEquals\": {\n" +
                "                    \"s3:prefix\": [\n" +
                "                        \"*\"\n" +
                "                    ]\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"Effect\": \"Allow\",\n" +
                "            \"Principal\": {\n" +
                "                \"AWS\": [\n" +
                "                    \"*\"\n" +
                "                ]\n" +
                "            },\n" +
                "            \"Action\": [\n" +
                "                \"s3:GetObject\"\n" +
                "            ],\n" +
                "            \"Resource\": [\n" +
                "                \"arn:aws:s3:::" + bucketName + "/**\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}");
        return builder;
    }
}