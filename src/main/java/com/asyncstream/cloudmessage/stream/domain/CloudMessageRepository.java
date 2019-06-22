package com.asyncstream.cloudmessage.stream.domain;

import java.util.List;

import com.asyncstream.cloudmessage.stream.domain.entity.CloudMessageEntity;
import com.asyncstream.cloudmessage.stream.domain.entity.CloudMessageKey;
import org.springframework.data.repository.CrudRepository;

public interface CloudMessageRepository extends CrudRepository<CloudMessageEntity, CloudMessageKey> {

    List<CloudMessageEntity> findByPkSender(String sender);

}
