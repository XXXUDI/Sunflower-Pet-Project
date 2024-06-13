package com.soCompany.sunflower.mapper;

import com.soCompany.sunflower.dto.ChatReadDto;
import com.soCompany.sunflower.entity.Chat;
import org.springframework.stereotype.Component;

@Component
public class ChatReadMapper implements Mapper<Chat, ChatReadDto>{
    @Override
    public ChatReadDto map(Chat from) {
        return ChatReadDto.builder()
                .id(from.getId())
                .name(from.getName())
                .community(from.getCommunity())
                .build();
    }
}
