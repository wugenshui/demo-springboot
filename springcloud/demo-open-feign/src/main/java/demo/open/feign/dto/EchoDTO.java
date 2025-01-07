package demo.open.feign.dto;

import java.util.Map;

public class EchoDTO {
    private String url;
    private String method;
    private Map<String, String> query; // 查询参数通常是以键值对的形式存在
    private Object body; // 请求体可以是任意对象，具体取决于API设计
    private HttpHeaders headers;

    // Getters and Setters

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getQuery() {
        return query;
    }

    public void setQuery(Map<String, String> query) {
        this.query = query;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }
}

class HttpHeaders {
    private Map<String, String> headers;

    // Constructor
    public HttpHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    // Getter and Setter
    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}