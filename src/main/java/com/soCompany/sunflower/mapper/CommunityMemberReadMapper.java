package com.soCompany.sunflower.mapper;

import com.soCompany.sunflower.dto.CommunityMemberReadDto;
import com.soCompany.sunflower.entity.CommunityMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommunityMemberReadMapper implements Mapper<CommunityMember, CommunityMemberReadDto> {

    private final CommunityReadMapper communityReadMapper;
    private final UserReadMapper userReadMapper;

    @Override
    public CommunityMemberReadDto map(CommunityMember from) {
        return CommunityMemberReadDto.builder()
                .id(from.getId())
                .communityReadDto(communityReadMapper.map(from.getCommunity()))
                .userReadDto(userReadMapper.map(from.getUser()))
                .role(from.getRole())
                .build();
    }
}
