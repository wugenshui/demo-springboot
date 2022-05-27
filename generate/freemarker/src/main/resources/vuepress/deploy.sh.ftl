#!/bin/sh
# 目录,$1为Jenkinsfile执行脚本传入的参数,映射到构建文件的根目录,例如/usr/local/demo_web/develop
DIRECTORY=/usr/local/$1
# 应用名称,项目的artifactId，例如 test-doc（需要调整）
APP_NAME=${name}
# 应用版本,例如 0.0.1（需要调整）
APP_VERSION=1.0.0
# 应用对外暴露端口,例如8080（需要调整）
APP_PORT=9000
# Harbor镜像仓库地址
HARBOR_SERVER=${registry}
# 是否启用镜像推送,true为开启,false为禁用
ENABLE_IMAGE_PUSH=true
# 普通消息输出：蓝色
echo_info() {
  echo -e "\033[34m [deploy.sh] $1 \033[0m"
}
# 异常消息输出：红色, 同时退出脚本，并通知脚本执行异常
echo_error() {
  echo -e "\033[31m [deploy.sh] $1 \033[0m"
  # 设置为不返回ture则脚本退出
  set -e
  # 返回1标识脚本执行异常，通知jenkins构建失败
  exit 1
}
# 镜像名称(设置为小写)
typeset -l IMAGE_FULL_NAME
# 容器名称(设置为小写)
typeset -l CONTAINER_NAME

# 容器名称，例如 company/sdms-order-service
IMAGE_FULL_NAME=${company}/$APP_NAME:$APP_VERSION
CONTAINER_NAME=$APP_NAME

cd $DIRECTORY
echo_info "start build docker image:$IMAGE_FULL_NAME"
docker build -f ./Dockerfile -t $IMAGE_FULL_NAME .
if [ $? -eq 0 ]; then
  # 镜像构建成功
  # 删除原有容器，防止端口占用
  docker rm -f $CONTAINER_NAME
  echo_info "start run docker container:$CONTAINER_NAME"
  # 运行新的容器（可以调整，增加项目相关配置，如目录挂载）
  docker run -d --name $CONTAINER_NAME \
  -p $APP_PORT:80 --restart=always $IMAGE_FULL_NAME
  # 推送镜像（仅master分支需要推送）
  if [ $ENABLE_IMAGE_PUSH = true ]; then
    docker tag $IMAGE_FULL_NAME $HARBOR_SERVER/$IMAGE_FULL_NAME \
    && docker push $HARBOR_SERVER/$IMAGE_FULL_NAME \
    && docker rmi $HARBOR_SERVER/$IMAGE_FULL_NAME
    echo_info "push image to harbor:$HARBOR_SERVER/$IMAGE_FULL_NAME"
  fi
  # 移除 tag 为 <none> 的公司内部镜像
  docker rmi $(docker images | grep ${company} | grep "<none>" | awk "{print $3}")
  # 如果服务启动较慢，可以暂停数秒后再去读取日志
  # sleep 10s
  # 打印容器运行日志
  echo_info "print container log"
  docker logs $CONTAINER_NAME --tail 500
else
  # 镜像构建失败
  echo_error "build docker image fail, exit"
fi