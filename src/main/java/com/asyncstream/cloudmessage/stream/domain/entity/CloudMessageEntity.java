package com.asyncstream.cloudmessage.stream.domain.entity;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("tbl_cloud_message")
public class CloudMessageEntity {

    @PrimaryKey
    private CloudMessageKey pk;

    @Column(value = "receiver")
    private String receiver;

    @Column(value = "message")
    private String message;

    public CloudMessageKey getPk() {
        return pk;
    }

    public void setPk(CloudMessageKey pk) {
        this.pk = pk;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
