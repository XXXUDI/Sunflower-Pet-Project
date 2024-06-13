package com.soCompany.sunflower.dto;

import com.soCompany.sunflower.entity.fields.CommunityMemberId;
import com.soCompany.sunflower.entity.fields.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityMemberEditDto {
    private CommunityMemberId communityMemberId;
    private Role role;
}
