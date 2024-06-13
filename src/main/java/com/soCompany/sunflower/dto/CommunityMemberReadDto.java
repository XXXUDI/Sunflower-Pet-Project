package com.soCompany.sunflower.dto;

import com.soCompany.sunflower.entity.fields.CommunityMemberId;
import com.soCompany.sunflower.entity.fields.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommunityMemberReadDto {
    private CommunityMemberId id;
    private CommunityReadDto communityReadDto;
    private UserReadDto userReadDto;
    private Role role;
}
