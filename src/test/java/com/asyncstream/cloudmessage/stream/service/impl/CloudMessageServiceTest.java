package com.asyncstream.cloudmessage.stream.service.impl;

import com.asyncstream.cloudmessage.stream.service.api.CloudMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudMessageServiceTest {

    @Autowired
    CloudMessageService cloudMessageService;

    @Test
    public void createMessageTest(){
        CloudMessage message = new CloudMessage();
        message.setSender("tester1@asyncstream.com");
        message.setReceiver("tester2@asyncstream.com");
        message.setMessage("Hello World");
        cloudMessageService.create(message);
    }

}
