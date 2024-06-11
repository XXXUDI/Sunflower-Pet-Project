package com.soCompany.sunflower.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Builder
@Entity
@Table(name = "Communities")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Community {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Integer id;

    @Column(nullable = false, name = "community_name")
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "logo_url")
    private String logotype;

    @Column(name ="banner_url")
    private String banner;

    @Column(name = "created_at",updatable = false, insertable = false)
    LocalDateTime createdAt;
}