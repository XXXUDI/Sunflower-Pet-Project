package com.soCompany.sunflower.entity.fields;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommunityMemberId implements Serializable {
    @Column(name = "community_id")
    private Integer communityId;
    @Column(name = "user_id")
    private Integer userId;
}