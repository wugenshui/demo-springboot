# 打包至中央仓库
[打包教程](https://segmentfault.com/a/1190000009450347?utm_source=tag-newest)

## gpg密钥
```
gpg --full-generate-key
# 1 2048 0

gpg --list-keys

gpg --keyserver http://keys.openpgp.org:11371 --send-keys FD55852D76EF2CCDD368BF7948C7B2E69D188A01

gpg --keyserver http://keys.openpgp.org:11371 --recv-keys FD55852D76EF2CCDD368BF7948C7B2E69D188A01

# 导出公钥、私钥
gpg -a -o public-file.key --export FD55852D76EF2CCDD368BF7948C7B2E69D188A01
gpg -a -o private-file.key --export-secret-keys FD55852D76EF2CCDD368BF7948C7B2E69D188A01

# 导入公钥、私钥
gpg --import public-file.key
gpg --import private-file.key
```