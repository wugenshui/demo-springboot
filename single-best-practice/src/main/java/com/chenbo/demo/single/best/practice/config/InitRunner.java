package com.chenbo.demo.single.best.practice.config;

import com.chenbo.demo.single.best.practice.constant.MinioConstant;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始化后执行操作
 *
 * @author : chenbo
 * @date : 2021-03-27
 */
@Slf4j
@Component
public class InitRunner implements CommandLineRunner {

    private final static String IMAGE = "image";
    private final static String VIDEO = "video";
    private final static List<String> BUCKETS = new ArrayList() {{
        add(IMAGE);
        add(VIDEO);
        add(MinioConstant.DEFAULT_BUCKET);
    }};


    @Autowired
    private MinioClient minioClient;

    @Override
    public void run(String... args) throws Exception {
        BUCKETS.forEach(b -> {
            try {
                // 检查minio 存储桶是否存在，不存在则初始化
                boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(b).build());
                if (!isExist) {
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(b).build());
                    log.error("makeBucket:" + b);
                }
            } catch (Exception e) {
                log.error("初始化桶异常", e);
            }
        });

    }
}
