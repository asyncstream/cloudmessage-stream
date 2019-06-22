package com.asyncstream.cloudmessage.stream.service.impl;

import com.asyncstream.cloudmessage.stream.domain.CloudMessageRepository;
import com.asyncstream.cloudmessage.stream.domain.entity.CloudMessageEntity;
import com.asyncstream.cloudmessage.stream.service.api.CloudMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindMessageCommand {
    private final CloudMessageRepository cloudMessageRepository;
    private final CloudMessageMapper cloudMessageMapper;

    public FindMessageCommand(CloudMessageRepository cloudMessageRepository,CloudMessageMapper cloudMessageMapper) {
        this.cloudMessageRepository = cloudMessageRepository;
        this.cloudMessageMapper = cloudMessageMapper;
    }

    public List<CloudMessage> execute(String sender){
        List<CloudMessageEntity> cloudMessageEntities = this.cloudMessageRepository.findByPkSender(sender);
        if(!cloudMessageEntities.isEmpty()) {
            return cloudMessageEntities.stream().map(e -> this.cloudMessageMapper.mapToPojo(e)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
