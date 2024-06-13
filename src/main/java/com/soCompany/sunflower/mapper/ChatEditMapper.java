package com.soCompany.sunflower.mapper;

import com.soCompany.sunflower.dto.ChatEditDto;
import com.soCompany.sunflower.entity.Chat;
import org.springframework.stereotype.Component;

@Component
public class ChatEditMapper implements Mapper<ChatEditDto, Chat>{
    @Override
    public Chat map(ChatEditDto from) {
        return Chat.builder()
                .name(from.getName())
                .community(from.getCommunity())
                .build();
    }

    @Override
    public Chat map(ChatEditDto object, Chat toObject) {
        toObject.setName(object.getName());
        toObject.setCommunity(object.getCommunity());
        return toObject;
    }
}
