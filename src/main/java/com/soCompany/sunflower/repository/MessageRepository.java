package com.soCompany.sunflower.repository;

import com.soCompany.sunflower.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    public List<Message> findAllByChat_Id(int chat_id);
}
