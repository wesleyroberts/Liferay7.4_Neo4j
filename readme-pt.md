**Tradução** 
- 🇺🇸[English](/readme.md)

# Objetivo do Repositório

Influênciar e formentar a integração do liferay 7.4 com o neo4j.
Com o portlet Neo4j conseguimos fazer chamadas cypher para noso Graphdatabase Neo4j.

## Driver Liferay-neo4j

O Liferay disponibiliza um drive Java Neo4j compatível com o OSGI.

Este driver envolve o driver oficial da linguagem JAva Neor4j adicionando compatibilidade OSGI. Atualmente estamos usando a versão 1.4.4 do driver Neo4j java. Para mais informações acesse o site official do Neo4j.

Liferay [neo4j-osgi-driver](https://repo1.maven.org/maven2/com/liferay/neo4j/neo4j-osgi-driver/)

## **Compatibilidade**

- Java 1.8 e Java 11✅
- Neo4j Java Driver 1.4.4 e Neo4j Server 3.3✅
- Testado no Liferay 7.4 ga112 ✅

## Definindo Ambiente local
Navegar até a pasta **local_env** e executar o seguinte comando:  

```
docker compose up
```  

## Configuration
Foi aplicado uma configuração ao Portlet Neo4j para conseguirmos inserir os dados do nosso graphdabase.
![](/images/neo4jconfig.png)

## Créditos

Tomei como base de inspirações os repositórios abaixos, que me ajudou bastante a finalizar a integração e ter um boa base de por onde serguir no desenvolvimento da integração.  

https://github.com/matethurzo/liferay-neo4j-driver/tree/master?tab=readme-ov-file

 https://github.com/danielkocsis/neo4j-sample-portlet/tree/v1.1