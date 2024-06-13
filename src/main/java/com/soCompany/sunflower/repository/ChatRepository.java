package com.soCompany.sunflower.repository;

import com.soCompany.sunflower.entity.Chat;
import com.soCompany.sunflower.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    public List<Chat> findAllByCommunity_Id(Integer id);

}
