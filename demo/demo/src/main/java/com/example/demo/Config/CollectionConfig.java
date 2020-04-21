package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import javax.annotation.PostConstruct;

@Configuration
@DependsOn("mongoTemplate")
public class CollectionConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void initIndexes() {

//        mongoTemplate.indexOps(User.class) // collection name string or .class
//                .ensureIndex(
////                        new Index().unique()
//                );
    }
}
