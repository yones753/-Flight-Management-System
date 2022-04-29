package com.example.FlightsManagementSystem.Contactus.DTO;

import javax.persistence.*;

@Entity
@Table(name = "Contactus")
public class ContactusDTO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int messageId;
    private String senderName;
    private String content;

    public ContactusDTO(){}

    public ContactusDTO(int messageId, String senderName, String content) {
        this.messageId = messageId;
        this.senderName = senderName;
        this.content = content;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ContactusDTO{" +
                "messageId=" + messageId +
                ", senderName='" + senderName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
