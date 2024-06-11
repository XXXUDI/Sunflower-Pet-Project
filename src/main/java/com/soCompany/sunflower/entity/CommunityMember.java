package com.soCompany.sunflower.entity;

import com.soCompany.sunflower.entity.fields.CommunityMemberId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "community_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityMember {

    @EmbeddedId
    private CommunityMemberId id;

    @Column(name = "role")
    private String role;

    @Column(name = "joined_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp joinedAt;

    @ManyToOne
    @JoinColumn(name = "community_id", insertable = false, updatable = false)
    private Community community;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
