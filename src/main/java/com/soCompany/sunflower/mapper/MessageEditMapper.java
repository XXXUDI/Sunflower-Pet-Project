package com.soCompany.sunflower.mapper;

import com.soCompany.sunflower.dto.MessageEditDto;
import com.soCompany.sunflower.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageEditMapper implements Mapper<MessageEditDto, Message>{
    @Override
    public Message map(MessageEditDto from) {
        return Message.builder()
                .chat(from.getChat())
                .user(from.getUser())
                .content(from.getContent())
                .build();
    }

    @Override
    public Message map(MessageEditDto object, Message toObject) {
        toObject.setChat(object.getChat());
        toObject.setUser(object.getUser());
        toObject.setContent(object.getContent());
        return toObject;
    }
}
