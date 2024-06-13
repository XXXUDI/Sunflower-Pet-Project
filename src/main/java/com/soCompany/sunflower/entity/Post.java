package com.soCompany.sunflower.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "posts")
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    @Column(name = "post_id")
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, name = "community_id")
    private Community community;

    @Column(name = "created_at",updatable = false, insertable = false)
    LocalDateTime createdAt;
}
