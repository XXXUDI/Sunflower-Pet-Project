package com.soCompany.sunflower.mapper;

import com.soCompany.sunflower.dto.PostEditDto;
import com.soCompany.sunflower.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostEditMapper implements Mapper<PostEditDto, Post>{

    @Override
    public Post map(PostEditDto from) {
        return Post.builder()
                .title(from.getTitle())
                .community(from.getCommunity())
                .user(from.getAuthor())
                .content(from.getContent())
                .build();
    }

    @Override
    public Post map(PostEditDto object, Post toObject) {
        toObject.setTitle(object.getTitle());
        toObject.setCommunity(object.getCommunity());
        toObject.setUser(object.getAuthor());
        toObject.setContent(object.getContent());
        return toObject;
    }
}
