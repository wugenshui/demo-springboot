package com.github.wugenshui.single.best.practice.controller.oss;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chenbo
 * @date : 2021-03-27
 */
@Api(tags = "图片文件上传")
@RestController
@RequestMapping("/oss/image")
public class ImageController {

    @PostMapping
    public void saveImage() {

    }
}
