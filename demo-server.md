---
title: RedDog Demo Server
breadcrums: ["Documentation", "documentation.html", "Introduction", "documentation.html#introduction"]
---

# {{ page.title }}

## Index

1. [Introduction](#introduction)
1. [Download](#download)
1. [Running the demo](#running-the-demo)
1. [Available features](#available-features)
1. [Available configuration](#available-configuration)
1. [Dummy data](#dummy-data)
   1. [Domain data](#domain-data)
   1. [Entity data](#entity-data)
   1. [Nameserver data](#nameserver-data)

## Introduction

Before you begin a full installation of RedDog, you can try its features out using our demo server. It is a standalone Tomcat binary packaged with RedDog and an embedded (H2-based) database populated with dummy data.

The only requirement is Java 8 or superior.

## Download

You can download the demo server from this [page](demo-download.html).

## Running the demo

Expand the compressed zip and run the jar normally:

	unzip rdap-server-demo-{{ site.latest-demo-server }}.zip
	cd rdap-demo-{{ site.latest-demo-server }}
	java -jar demo.jar

The server will run at [http://localhost:8080/rdap-server/](http://localhost:8080/rdap-server/) by default. You can change the binding IP and port, using the `java -jar demo.jar [your-IP-address [your-port]]` syntax. As an example:

	java -jar demo.jar 127.0.0.5 9090

When the server starts, the console will log messages as shown below. As long as you don't get SEVEREs (as opposed to INFOs), the server is underway.

![SERVER CONSOLE](img/demo-console.jpg)
 
The demo server is now ready to answer requests. In your browser, query the address that you set up and you should see a welcome page:
 
![WELCOME PAGE](img/demo-index.jpg)
 
## Available features

As a demo version, this server has limited features compared to the ones defined in [RFC 7482](https://tools.ietf.org/html/rfc7482) (excluding ASN and IPs queries), as well as the ones from the full server. Here are examples of queries that should be successful. See the [tables below](#dummy-data) for more options.

+ Domain query`*`: [`domain/goldfish.com`](http://localhost:8080/rdap-server/domain/goldfish.com)
+ Entity query: [`entity/mr_fish`](http://localhost:8080/rdap-server/entity/mr_fish)
+ Nameserver query: [`nameserver/ns2.chopsuey.net`](http://localhost:8080/rdap-server/nameserver/ns2.chopsuey.net)
+ Domains search`*`: [`domains?name=cone*`](http://localhost:8080/rdap-server/domains?name=cone*)
+ Entities search: [`entities?handle=don_*`](http://localhost:8080/rdap-server/entities?handle=don_*)
+ Nameservers search: [`nameservers?name=ns1.*`](http://localhost:8080/rdap-server/nameservers?name=ns1.*)
+ Help request: [`help`](http://localhost:8080/rdap-server/help)

`*` Because of the shipped configuration of `zones` (see below), only **'.com'** and **'.com.example'** domains will yield success.

## Available configuration

Although this a demo version with limited features, some configurations can be customized so that you can learn more about the server behavior. The following list mentions the files that can be used to configure the demo server:
* `WEB-INF/configuration.properties` is RedDog's global configuration file, to tweak it learn more at [Configuring RedDog's Server Behavior](behavior-configuration.html).
* `WEB-INF/data-access.properties` is the implementation configuration file, to tweak it learn more at [Configuring RedDog's reference implementation](data-access-configuration.html).
* `WEB-INF/shiro.ini` is Apache Shiro's configuration file used by RedDog, useful for security settings such as: resources protection, user authentication, user roles, etc. To tweak it learn more at [Using Apache Shiro](using-apache-shiro.html).
* `WEB-INF/privacy` is the folder where privacy settings can be configured, goes along with security configurations. To tweak it learn more at [Configuring Response Privacy](response-privacy.html).
* `WEB-INF/notices/help.xml` is the file where the 'help' response can be customized, to tweak it learn more at [Configuring RedDog's Help Response](help-response.html).
* Optionally a file `WEB-INF/notices/tos.xml` can be added to set the server's terms of service, learn more at [Configuring RedDog's Terms of Service](terms-of-service.html).

> ![Warning](img/warning.svg) Notice that tweaks to any of these files require a server restart to go live.

## Dummy data

The demo database ships with the following test data:

### Domain data

| Handle   | ldh (letter, digit, hyphen) name | Unicode name     | Zone         |
|:---------|:-------------------------------- |:-----------------|:-------------|
| DOM1     | whiterabbit                      |                  | com          |
| DOMCOM   | goldfish                         |                  | com          |
| XXX2     | reddog                           |                  | com          |
| 1234     | blackcat                         |                  | com          |
| ylb      | yellowbird                       |                  | com          |
| DOM2     | conejo_blanco                    |                  | com.example  |
| DOMCOMMX | pez_dorado                       |                  | com.example  |
| XXX3     | perro_rojo                       |                  | com.example  |
| 1235     | gato_negro                       |                  | com.example  |
| pjra     | pajaro_amarillo                  |                  | com.example  |
| DOM3     | conejo_blanco                    |                  | example      |
| DOMMX    | pez_dorado                       |                  | example      |
| XXX4     | perro_rojo                       |                  | example      |
| 1236     | gato_negro                       |                  | example      |
|          | pajaro_amarillo                  |                  | example      |
| DOM4     | choco                            |                  | test         |
| DOMLAT   | moka                             |                  | test         |
| XXX6     | 1.0.168.192                      |                  | in-addr.arpa |
| 1238     | xn--mxico-bsa                    | méxico           | test         |
| xnxn     | xn--elpjaroamarillo-pjb          | elpájaroamarillo | test         |

### Entity data

| Handle     | Full Name |
|:-----------|:----------|
| mr_rabbit  | Bill      |
| mr_fish    | Billy     |
| mr_dog     | Bob       |
| mr_cat     | Barry     |
| mr_bird    | Wonka     |
| don_conejo | Tristan   |
| don_pez    | Shane     |
| don_perro  | Layne     |
| don_gato   | Brittney  |
| don_pajaro | Blair     |
| sr_conejo  | Gary      |
| sr_pez     | Gepetto   |
| sr_perro   | Cindy     |
| sr_gato    | Roy       |
| sr_pajaro  |           |
| cone       |           |
| pez        |           |
| perr       |           |
| gat        |           |
| paj        |           |

### Nameserver data

| Handle | ldh(letter, digit, hyphen) name | Unicode name           | IP Address      |
|:-------|:--------------------------------|:-----------------------|:----------------|
| NSE1   | ns1.chopsuey.net                |                        | 192.168.1.1     |
| NSE2   | ns2.chopsuey.net                |                        | 192.168.1.2     |
| NSE3   | ns3.chopsuey.net                |                        | 192.168.1.3     |
| NSE4   | ns4.chopsuey.net                |                        | 1:0:0:0:0:0:0:1 |
| NSE5   | ns5.chopsuey.net                |                        | 2:0:0:0:0:0:0:2 |
| NSE6   | ns1.white.example               |                        | 192.168.1.4     |
| NSE7   | ns2.white.example               |                        | 192.168.1.5     |
| NSE8   | ns3.white.example               |                        | 192.168.1.6     |
| NSE9   | ns4.white.example               |                        | 192.168.1.7     |
| NSE10  | ns5.white.example               |                        | 192.168.1.8     |
| NSE11  | ns1.bright.info                 |                        |                 |
| NSE12  | ns2.bright.info                 |                        |                 |
| NSE13  | ns3.bright.info                 |                        |                 |
| NSE14  | ns4.bright.info                 |                        |                 |
| NSE15  | ns5.bright.info                 |                        |                 |
| NSE16  | ns1.camión.test                 | ns1.xn--camin-3ta.test |                 |
| NSE17  | ns2.camión.test                 | ns2.xn--camin-3ta.test |                 |
| NSE18  | ns3.camión.test                 | ns3.xn--camin-3ta.test |                 |
| NSE19  | ns4.camión.test                 | ns4.xn--camin-3ta.test |                 |
| NSE20  | ns5.camión.test                 | ns5.xn--camin-3ta.test |                 |
