# best-practice

## uaa 认证授权服务

POST http://client:secret@localhost:8000/oauth/token?grant_type=password&username=client&password=secret

## 异常

### 400 {“error”:“invalid_grant”,“error_description”:“Bad credentials”}
- 分析：密码错误