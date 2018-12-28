# spring-elasticsearch
###docker安装elasticsearch（https://hub.docker.com/_/elasticsearch）
####安装docker   
 >没有安装过就自己去安装，有点慢，最好科学上网,还有换国内的镜像源。
####安装elasticsearch
+ docker pull elasticsearch:6.5.4
> 我一开始想用latest,发现根本下不下来，没有办法找了个最新版
####启动elasticsearch
+ docker run -d --name=elasticsearch -p 9200:9200 -p 9300:9300 -v yourFilePath:contailerFilePath  elasticsearch:6.5.4
> --name=容器的名称 -p 宿主机端口:容器的端口  -v 宿主机的目录（绝对地址）: 容器地址

>

> https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/     (表2.方法名称中支持的关键字)