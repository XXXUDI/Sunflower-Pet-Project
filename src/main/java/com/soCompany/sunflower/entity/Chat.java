package com.soCompany.sunflower.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "Chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @Column(name = "chat_name")
    private String name;

    @Column(name = "created_at",updatable = false, insertable = false)
    LocalDateTime createdAt;
}

