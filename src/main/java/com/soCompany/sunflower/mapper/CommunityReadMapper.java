package com.soCompany.sunflower.mapper;

import com.soCompany.sunflower.dto.CommunityReadDto;
import com.soCompany.sunflower.entity.Community;

public class CommunityReadMapper implements Mapper<Community, CommunityReadDto> {

    @Override
    public CommunityReadDto map(Community from) {
        return CommunityReadDto.builder()
                .name(from.getName())
                .description(from.getDescription())
                .logotype(from.getLogotype())
                .banner(from.getBanner())
                .build();
    }
}