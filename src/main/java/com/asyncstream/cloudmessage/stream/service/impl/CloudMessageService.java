package com.asyncstream.cloudmessage.stream.service.impl;

import com.asyncstream.cloudmessage.stream.service.api.CloudMessage;
import com.asyncstream.cloudmessage.stream.service.api.ICloudMessage;
import com.datastax.driver.core.utils.UUIDs;

import java.util.List;
import java.util.UUID;

public class CloudMessageService implements ICloudMessage {

    private CreateMessageCommand createMessageCommand;
    private FindMessageCommand findMessageCommand;
    public CloudMessageService(CreateMessageCommand createMessageCommand,FindMessageCommand findMessageCommand) {
        this.createMessageCommand = createMessageCommand;
        this.findMessageCommand = findMessageCommand;
    }


    @Override
    public UUID create(CloudMessage cloudMessage) {
        cloudMessage.setId(UUIDs.timeBased());
        return this.createMessageCommand.execute(cloudMessage);
    }

    @Override
    public List<CloudMessage> getBySender(String sender) {
        return this.findMessageCommand.execute(sender);
    }
}
