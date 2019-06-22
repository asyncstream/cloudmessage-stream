package com.asyncstream.cloudmessage.stream.service.impl;

import com.asyncstream.cloudmessage.stream.domain.entity.CloudMessageEntity;
import com.asyncstream.cloudmessage.stream.domain.entity.CloudMessageKey;
import com.asyncstream.cloudmessage.stream.service.api.CloudMessage;
import org.springframework.stereotype.Component;

@Component
public class CloudMessageMapper {

    CloudMessage mapToPojo(CloudMessageEntity cloudMessageEntity){
        CloudMessage cloudMessage = new CloudMessage();
        cloudMessage.setId(cloudMessageEntity.getPk().getId());
        cloudMessage.setSender(cloudMessageEntity.getPk().getSender());
        cloudMessage.setReceiver(cloudMessageEntity.getReceiver());
        cloudMessage.setMessage(cloudMessageEntity.getMessage());
        return cloudMessage;
    }

    CloudMessageEntity mapToDomain(CloudMessage cloudMessage){
        CloudMessageKey key =new CloudMessageKey();
        key.setSender(cloudMessage.getSender());
        key.setId(cloudMessage.getId());
        CloudMessageEntity entity = new CloudMessageEntity();
        entity.setPk(key);
        entity.setReceiver(cloudMessage.getReceiver());
        entity.setMessage(cloudMessage.getMessage());
        return entity;
    }
}
