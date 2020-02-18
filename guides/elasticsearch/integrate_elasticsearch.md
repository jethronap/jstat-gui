# Integrate Elastic Search

## Add to the project's ```pom.xml``` the following  entries

```
<!-- https://mvnrepository.com/artifact/org.springframework.data/spring-data-elasticsearch -->
<dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-elasticsearch</artifactId>
            <version>3.2.4.RELEASE</version>
</dependency>
```

```
  <!-- https://mvnrepository.com/artifact/org.elasticsearch/elasticsearch -->
  <dependency>
              <groupId>org.elasticsearch</groupId>
              <artifactId>elasticsearch</artifactId>
              <version>6.0.1</version>
  </dependency>
  
  
   <!-- https://mvnrepository.com/artifact/org.elasticsearch.client/elasticsearch-rest-high-level-client -->
  <dependency>
              <groupId>org.elasticsearch.client</groupId>
              <artifactId>elasticsearch-rest-high-level-client</artifactId>
              <version>6.0.1</version>
  </dependency>
```

Consult the relevant maven repository for any new releases. 