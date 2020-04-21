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
public class AdminConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void initIndexes()
    {
        mongoTemplate.indexOps("admin")
                .ensureIndex(
                        new Index().on("terminated_at", Sort.Direction.ASC).expire(0)
                );
    }

}
