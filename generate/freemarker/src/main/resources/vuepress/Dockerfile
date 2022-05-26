FROM nginx:1.21.0-alpine

ENV LANG C.UTF-8

# 设置容器时间
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
# 设置容器时区
RUN echo 'Asia/Shanghai' >/etc/timezone

COPY . /usr/share/nginx/html/

EXPOSE 80