/**
 * 
 */
package com.ciber.springRest.mongodb.ApiRestMongoDB.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * @author ciber
 *
 */
@Configuration
@EnableMongoRepositories(basePackages="com.ciber.springBoot.daoUsers")
public class SimpleMongoConfig {
  
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient("localhost");
    }
    
    
    @Bean
    public MongoTemplate mongoLogin() throws Exception {
        return new MongoTemplate(mongo(), "usuarioslogin");
    }

   
}
