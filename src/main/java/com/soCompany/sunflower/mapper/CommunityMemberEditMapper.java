package com.soCompany.sunflower.mapper;

import com.soCompany.sunflower.dto.CommunityMemberEditDto;
import com.soCompany.sunflower.entity.Community;
import com.soCompany.sunflower.entity.CommunityMember;
import com.soCompany.sunflower.entity.User;
import com.soCompany.sunflower.repository.CommunityRepository;
import com.soCompany.sunflower.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommunityMemberEditMapper implements Mapper<CommunityMemberEditDto, CommunityMember>{

    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;


    @Override
    public CommunityMember map(CommunityMemberEditDto from) {

        Community community = communityRepository.findById(from.getCommunityMemberId().getCommunityId())
                .orElseThrow(() -> new EntityNotFoundException("Community not found with id: " + from.getCommunityMemberId().getCommunityId()));

        User user = userRepository.findById(from.getCommunityMemberId().getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + from.getCommunityMemberId().getUserId()));

        return CommunityMember.builder()
                .id(from.getCommunityMemberId())
                .community(community)
                .user(user)
                .role(from.getRole())
                .build();
    }

    @Override
    public CommunityMember map(CommunityMemberEditDto object, CommunityMember toObject) {

        Community community = communityRepository.findById(object.getCommunityMemberId().getCommunityId())
                .orElseThrow(() -> new EntityNotFoundException("Community not found with id: " + object.getCommunityMemberId().getCommunityId()));

        User user = userRepository.findById(object.getCommunityMemberId().getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + object.getCommunityMemberId().getUserId()));


        toObject.setId(object.getCommunityMemberId());
        toObject.setCommunity(community);
        toObject.setUser(user);
        toObject.setRole(object.getRole());
        return toObject;
    }
}
