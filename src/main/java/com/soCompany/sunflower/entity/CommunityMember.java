package com.soCompany.sunflower.entity;

import com.soCompany.sunflower.entity.fields.CommunityMemberId;
import com.soCompany.sunflower.entity.fields.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "community_members")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityMember {

    @EmbeddedId
    private CommunityMemberId id;

    @Column(name = "role", nullable = false, length = 20, columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "joined_at", updatable = false, insertable = false)
    private LocalDateTime joinedAt;

    @ManyToOne
    @JoinColumn(name = "community_id", insertable = false, updatable = false)
    private Community community;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
