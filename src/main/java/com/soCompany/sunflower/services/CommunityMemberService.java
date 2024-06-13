package com.soCompany.sunflower.services;



import com.querydsl.jpa.impl.JPAQuery;
import com.soCompany.sunflower.dto.CommunityMemberEditDto;
import com.soCompany.sunflower.dto.CommunityMemberReadDto;
import com.soCompany.sunflower.dto.UserReadDto;
import com.soCompany.sunflower.entity.*;
import com.soCompany.sunflower.entity.fields.CommunityMemberId;
import com.soCompany.sunflower.mapper.CommunityMemberEditMapper;
import com.soCompany.sunflower.mapper.CommunityMemberReadMapper;
import com.soCompany.sunflower.mapper.UserReadMapper;
import com.soCompany.sunflower.repository.CommunityMemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.soCompany.sunflower.entity.QCommunityMember.communityMember;
import static com.soCompany.sunflower.entity.QUser.user;

@Service
@RequiredArgsConstructor
public class CommunityMemberService {

    private final EntityManager entityManager;
    private final UserReadMapper userReadMapper;
    private final CommunityMemberRepository communityMemberRepository;
    private final CommunityMemberEditMapper communityMemberEditMapper;
    private final CommunityMemberReadMapper communityMemberReadMapper;

    public List<UserReadDto> findAllUsersByCommunityId(Integer communityId) {
        var userList = new JPAQuery<User>(entityManager)
                .select(user)
                .from(communityMember)
                .where(communityMember.community.id.eq(communityId))
                .fetch();

        return userList.stream().map(userReadMapper::map).toList();
    }

    public Optional<CommunityMemberReadDto> findCommunityMemberById(CommunityMemberId communityMemberId) {
        return communityMemberRepository.findById(communityMemberId).map(communityMemberReadMapper::map);
    }

    public CommunityMemberReadDto save(CommunityMemberEditDto communityMemberEditDto) {
        return Optional.of(communityMemberEditDto)
                .map(communityMemberEditMapper::map)
                .map(communityMemberRepository::saveAndFlush)
                .map(communityMemberReadMapper::map).orElseThrow();
    }

    public Optional<CommunityMemberReadDto> update(CommunityMemberEditDto communityMemberEditDto) {
        return communityMemberRepository.findById(communityMemberEditDto.getCommunityMemberId())
                .map(member -> communityMemberEditMapper.map(communityMemberEditDto, member))
                .map(communityMemberRepository::saveAndFlush)
                .map(communityMemberReadMapper::map);
    }

    public boolean delete(CommunityMemberId communityMemberId) {
        return communityMemberRepository.findById(communityMemberId)
                .map(member -> {
                    communityMemberRepository.delete(member);
                    return true;
                }).orElse(false);
    }
}
