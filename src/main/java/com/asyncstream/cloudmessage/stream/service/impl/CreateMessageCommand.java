package com.asyncstream.cloudmessage.stream.service.impl;

import com.asyncstream.cloudmessage.stream.domain.CloudMessageRepository;
import com.asyncstream.cloudmessage.stream.domain.entity.CloudMessageEntity;
import com.asyncstream.cloudmessage.stream.service.api.CloudMessage;

import java.util.UUID;

public class CreateMessageCommand {

    private final CloudMessageRepository cloudMessageRepository;
    private final CloudMessageMapper cloudMessageMapper;
    public CreateMessageCommand(CloudMessageRepository cloudMessageRepository,CloudMessageMapper cloudMessageMapper) {
        this.cloudMessageRepository = cloudMessageRepository;
        this.cloudMessageMapper = cloudMessageMapper;
    }

    public UUID execute(CloudMessage cloudMessage){
        CloudMessageEntity entity= this.cloudMessageRepository.save(this.cloudMessageMapper.mapToDomain(cloudMessage));
        return  entity.getPk().getId();
    }
}
