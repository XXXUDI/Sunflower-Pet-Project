package com.soCompany.sunflower.repository;

import com.soCompany.sunflower.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
