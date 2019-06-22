package com.asyncstream.cloudmessage.stream.config;

import com.asyncstream.cloudmessage.stream.domain.CloudMessageRepository;
import com.asyncstream.cloudmessage.stream.service.impl.CloudMessageMapper;
import com.asyncstream.cloudmessage.stream.service.impl.CloudMessageService;
import com.asyncstream.cloudmessage.stream.service.impl.CreateMessageCommand;
import com.asyncstream.cloudmessage.stream.service.impl.FindMessageCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CreateMessageCommand createMessageCommand(CloudMessageRepository cloudMessageRepository, CloudMessageMapper cloudMessageMapper){
        return new CreateMessageCommand(cloudMessageRepository, cloudMessageMapper);
    }

    @Bean
    public FindMessageCommand findMessageCommand(CloudMessageRepository cloudMessageRepository,CloudMessageMapper cloudMessageMapper){
        return new FindMessageCommand(cloudMessageRepository,cloudMessageMapper);
    }

    @Bean
    public CloudMessageService cloudMessageService(CreateMessageCommand createMessageCommand,FindMessageCommand findMessageCommand){
        return new CloudMessageService(createMessageCommand,findMessageCommand);
    }
}
