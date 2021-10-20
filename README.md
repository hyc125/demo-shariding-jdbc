## 数据库

准备环境

```shell
export img_name=sharding-jdbc-demo:0.1
export c_name=sharding-jdbc-mysql

docker image build -t ${img_name} .
docker container run -dit --name ${c_name} -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 ${img_name}
docker container exec -it ${c_name} mysql -uroot -p1234
```

清理环境

```shell
export img_name=sharding-jdbc-demo:0.1
export c_name=sharding-jdbc-mysql
docker container stop ${c_name}
docker container rm ${c_name}
docker image rm ${img_name}

```

