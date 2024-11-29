package com.github.wugenshui.baseutil.baseutil.spring.rest;

import cn.hutool.core.io.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author : chenbo
 * @date : 2024-11-29
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RestTemplateTest {
    @Resource
    private RestTemplate restTemplate;

    @Test
    public void test() {
        // 接口地址
        String url = "http://192.168.0.89:8082/office/convert/pdf";

        // 模拟本地文件
        String filePath = "C:\\Users\\xxxx\\Documents\\中华人民共和国民法典.docx";
        File file = new File(filePath);

        // 构造参数发起请求
        MultiValueMap<String, Object> params  = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        // 添加上传文件
        params.add("file", new FileSystemResource(file));
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
        byte[] responseData = restTemplate.postForObject(url, requestEntity, byte[].class);

        // 响应的内容可以保存成文件
        assert responseData != null;
        FileUtil.writeBytes(responseData, "D://1.pdf");
    }
}
