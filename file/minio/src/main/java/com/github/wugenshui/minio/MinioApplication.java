package com.github.wugenshui.minio;

import io.minio.BucketExistsArgs;
import io.minio.GetBucketTagsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.SetBucketPolicyArgs;
import io.minio.StatObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.http.Method;
import io.minio.messages.Tags;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

/**
 * @author : chenbo
 * @date : 2020-11-20
 */
public class MinioApplication {
    public static void main(String[] args) throws Exception {
        String bucketName = "image";

        MinioClient minioClient = MinioClient
                .builder()
                .endpoint("http://192.168.0.222:9000")
                .credentials("minio", "minio2021")
                .build();

        Tags bucketTags = minioClient.getBucketTags(GetBucketTagsArgs.builder().bucket(bucketName).build());

        // 桶
        boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!isExist) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } else {
            System.out.println("Bucket already exists.");
        }

        // 设置桶访问策略
        minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(defaultBucketPolicy(bucketName)).build());

        ClassPathResource resource = new ClassPathResource("cat.jpg");
        System.out.println("resource = " + resource.getPath());
        // 上传
        minioClient.uploadObject(UploadObjectArgs.builder()
                .bucket(bucketName)
                .object("cat-max.jpg")
                .filename(resource.getFile().getAbsolutePath())
                .build());

        String image = minioClient.getObjectUrl(bucketName, "cat-max.jpg");
        System.out.println(image);

        // 获取元数据
        ObjectStat objectStat = minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object("cat-max.jpg").build());
        System.out.println(objectStat);

        // 获取流数据
        InputStream inputSteam = minioClient.getObject(
                GetObjectArgs.builder().bucket(bucketName)
                        .object("cat-max.jpg").build());

        // 获取下载地址
        String url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder().bucket(bucketName).method(Method.GET)
                        .object("cat-max.jpg").expiry(24 * 60 * 60).build());
        System.out.println(url);
    }

    /**
     * 获取默认桶策略,默认读取不需要权限
     *
     * @param bucketName 桶名称
     * @return
     */
    private static String defaultBucketPolicy(String bucketName) {
        return "{\n" +
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
                "}";
    }
}

