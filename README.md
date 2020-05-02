# demo-spring-boot
demo-springboot

demo project for spring-boot

### JWT密钥生成命令
```
# 生成私钥文件
# alias 别名
# keyalg 指定密钥的算法 (如 RSA  DSA（如果不指定默认采用DSA）)
# validity 有效天数
# keypass 密钥口令
# storepass 密钥库口令
keytool -genkeypair -alias practice -keyalg RSA -keypass practice -keystore practice.jks -storepass practice -validity 1000

# 生成公钥
#keytool -export -alias practice -keystore practice.jks -file practice.publicKey
# keytool -list -rfc --keystore practice.jks | openssl x509 -inform pem -pubkey


# 下载openssl工具
windows版本： http://slproweb.com/products/Win32OpenSSL.html
```