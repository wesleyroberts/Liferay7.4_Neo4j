**Translate** 
- 🇧🇷[portuguese](/readme-pt.md)

# Purpose of the Repository

Influence and shape the integration of liferay 7.4 with neo4j.
With the Neo4j portlet we can make cypher calls to our Neo4j Graphdatabase.

## Liferay-neo4j Driver

Liferay provides an OSGI-compatible Java Neo4j drive.

This driver wraps the official Java Neor4j language driver adding OSGI compatibility. We are currently using version 1.4.4 of the Neo4j java driver. For more information visit the official Neo4j website.

Liferay [neo4j-osgi-driver](https://repo1.maven.org/maven2/com/liferay/neo4j/neo4j-osgi-driver/)

## **Compatibility**

- Java 1.8 and Java 11 ✅
- Neo4j Java Driver 1.4.4 and Neo4j Server 3.3✅
- Tested on Liferay 7.4 ga112 ✅

## Settings
A configuration was applied to the Neo4j Portlet to be able to insert data from our graphdabase.
![](/images/neo4jconfig.png)
a
## Credits

I took the repositories below as inspiration, which helped me a lot to finalize the integration and have a good basis for where to go in the development of the integration.  

https://github.com/matethurzo/liferay-neo4j-driver/tree/master?tab=readme-ov-file

 https://github.com/danielkocsis/neo4j-sample-portlet/tree/v1.1