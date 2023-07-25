# best-practice

## uaa 认证授权服务

### 授权码模式 authorization code
- 授权码模式（authorization code）是功能最完整、流程最严密的授权模式。它的特点就是通过客户端的后台服务器，与"服务提供商"的认证服务器进行互动。

1. 直接访问地址，输入账户密码跳转至登录界面，获取code `http://localhost:8000/oauth/authorize?grant_type=authorization_code&response_type=code&client_id=client&state=1234&redirect_uri=https://www.baidu.com`
2. `curl client:secret@localhost:8000/oauth/token -dgrant_type=authorization_code -dredirect_uri=https://www.baidu.com -dcode=9cAurT`

### 密码模式 resource owner password credentials
- 密码模式（Resource Owner Password Credentials Grant）中，用户向客户端提供自己的用户名和密码。客户端使用这些信息，向"服务商提供商"索要授权。

`curl client:secret@localhost:8000/oauth/token -dgrant_type=password -dscope=all -dusername=user -dpassword=user`

### 简化模式 implicit
- 简化模式（implicit grant type）不通过第三方应用程序的服务器，直接在浏览器中向认证服务器申请令牌，跳过了"授权码"这个步骤，因此得名。

直接访问地址，输入账户密码跳转至登录界面，获取token `http://localhost:8000/oauth/authorize?grant_type=implicit&response_type=token&client_id=client&state=1234&redirect_uri=https://www.baidu.com`

### 客户端授权模式 client credentials
- 客户端模式（Client Credentials Grant）指客户端以自己的名义，而不是以用户的名义，向"服务提供商"进行认证。严格地说，客户端模式并不属于OAuth框架所要解决的问题。

`curl client:secret@localhost:8000/oauth/token -dgrant_type=client_credentials -dscope=all`

## 异常

### 400 {“error”:“invalid_grant”,“error_description”:“Bad credentials”}
- 分析：密码错误
