package com.chenbo.demo.thumbnailator;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * @author : chenbo
 * @date : 2020-11-18
 */
@SpringBootApplication
public class ThumbnailatorApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(ThumbnailatorApplication.class, args);

        double scale = 0.6f;
        double quality = 0.6f;

        Thumbnails.of("C:/Users/chenbo/Desktop/1.jpg")
                .scale(scale)
                .outputQuality(quality)
                .toFile("C:/Users/chenbo/Desktop/1.1.jpg");

        Thumbnails.of("C:/Users/chenbo/Desktop/2.jpg")
                .scale(scale)
                .outputQuality(quality)
                .toFile("C:/Users/chenbo/Desktop/2.2.jpg");


        Thumbnails.of("C:/Users/chenbo/Desktop/3.png")
                .scale(scale)
                .outputQuality(quality)
                .toFile("C:/Users/chenbo/Desktop/3.3.jpg");
    }
}
