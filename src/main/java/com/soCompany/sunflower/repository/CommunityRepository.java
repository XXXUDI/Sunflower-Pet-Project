package com.soCompany.sunflower.repository;

import com.soCompany.sunflower.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
    public Optional<Community> findByName(String name);

    public Optional<Community> findById(int id);
}
