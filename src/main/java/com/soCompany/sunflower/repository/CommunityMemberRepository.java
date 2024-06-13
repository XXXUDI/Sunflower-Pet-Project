package com.soCompany.sunflower.repository;

import com.soCompany.sunflower.entity.Community;
import com.soCompany.sunflower.entity.CommunityMember;
import com.soCompany.sunflower.entity.fields.CommunityMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityMemberRepository extends JpaRepository<CommunityMember, CommunityMemberId> {

    public List<CommunityMember> findByCommunity(Community community);
}
