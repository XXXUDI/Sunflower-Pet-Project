package com.soCompany.sunflower.repository;

import com.soCompany.sunflower.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
