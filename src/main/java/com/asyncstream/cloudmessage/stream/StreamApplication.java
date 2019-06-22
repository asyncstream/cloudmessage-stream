package com.asyncstream.cloudmessage.stream;


import com.asyncstream.cloudmessage.stream.config.BeanConfig;
import com.asyncstream.cloudmessage.stream.config.DbConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableCassandraRepositories(basePackages = { "com.asyncstream.cloudmessage.stream" })
@Import({DbConfiguration.class, BeanConfig.class})
public class StreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamApplication.class, args);
    }

}
