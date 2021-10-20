FROM mysql:8.0 AS sharding-jdbc-mysql
MAINTAINER eason_huang huang_yicheng@foxmail.com

RUN mkdir "sql"
COPY ./sql/* /docker-entrypoint-initdb.d/
