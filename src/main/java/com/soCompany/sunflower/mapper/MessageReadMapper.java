package com.soCompany.sunflower.mapper;

import com.soCompany.sunflower.dto.MessageReadDto;
import com.soCompany.sunflower.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageReadMapper implements Mapper<Message, MessageReadDto> {
    @Override
    public MessageReadDto map(Message from) {
        return MessageReadDto.builder()
                .id(from.getId())
                .chat(from.getChat())
                .user(from.getUser())
                .build();
    }
}
