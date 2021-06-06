# 授权模式

## 授权码模式 authorization code
- 授权码模式（authorization code）是功能最完整、流程最严密的授权模式。它的特点就是通过客户端的后台服务器，与"服务提供商"的认证服务器进行互动。

1. 直接访问地址，输入账户密码跳转至登录界面，获取code `http://localhost:8080/oauth/authorize?grant_type=authorization_code&response_type=code&client_id=client&state=1234&redirect_uri=http://www.baidu.com`
2. `curl client:secret@localhost:8080/oauth/token -dgrant_type=authorization_code -dcode=9cAurT -dredirect_uri=http://www.baidu.com`

## 密码模式 resource owner password credentials

`curl client:secret@localhost:8080/oauth/token -dgrant_type=password -dscope=all -dusername=user -dpassword=password`

## 简化模式 implicit
1. 直接访问地址，输入账户密码跳转至登录界面，获取token `http://localhost:8080/oauth/authorize?grant_type=implicit&response_type=token&client_id=client&state=1234&redirect_uri=http://www.baidu.com`

## 客户端授权模式 client credentials

`curl client:secret@localhost:8080/oauth/token -dgrant_type=client_credentials -dscope=all`
