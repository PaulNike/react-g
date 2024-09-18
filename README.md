# react-g

Zookeper:
1. C:\kafka\kafka_2.12-3.7.0\bin> .\bin\windows\zookeeper-server-start.bat ..\bin\config\zookeeper.properties

Kafka:
1. .\bin\windows\kafka-server-start.bat .\config\server.properties

Creando Topico:
.\bin\windows\kafka-topics.bat --create --topic person-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
