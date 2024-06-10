package com.soCompany.sunflower.repository;

import com.soCompany.sunflower.entity.CommunityMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityMemberRepository extends JpaRepository<CommunityMember, Long> {
}
