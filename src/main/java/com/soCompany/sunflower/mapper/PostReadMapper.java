package com.soCompany.sunflower.mapper;

import com.soCompany.sunflower.dto.PostReadDto;
import com.soCompany.sunflower.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostReadMapper implements Mapper<Post, PostReadDto> {
    @Override
    public PostReadDto map(Post from) {
        return PostReadDto.builder()
                .id(from.getId())
                .title(from.getTitle())
                .content(from.getContent())
                .author(from.getUser())
                .community(from.getCommunity())
                .build();
    }
}
