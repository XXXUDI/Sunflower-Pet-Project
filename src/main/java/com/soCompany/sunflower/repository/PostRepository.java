package com.soCompany.sunflower.repository;

import com.soCompany.sunflower.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
