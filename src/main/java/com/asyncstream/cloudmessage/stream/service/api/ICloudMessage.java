package com.asyncstream.cloudmessage.stream.service.api;

import java.util.List;
import java.util.UUID;

public interface ICloudMessage {

    UUID create(CloudMessage cloudMessage);
    List<CloudMessage> getBySender(String sender);

}
