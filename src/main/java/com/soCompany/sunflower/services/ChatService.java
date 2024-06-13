package com.soCompany.sunflower.services;

import com.soCompany.sunflower.dto.ChatEditDto;
import com.soCompany.sunflower.dto.ChatReadDto;
import com.soCompany.sunflower.entity.Chat;
import com.soCompany.sunflower.entity.Community;
import com.soCompany.sunflower.mapper.ChatEditMapper;
import com.soCompany.sunflower.mapper.ChatReadMapper;
import com.soCompany.sunflower.repository.ChatRepository;
import com.soCompany.sunflower.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final CommunityRepository communityRepository;
    private final ChatRepository chatRepository;
    private final ChatEditMapper chatEditMapper;
    private final ChatReadMapper chatReadMapper;

    public Optional<ChatReadDto> findById(int id) {
        return chatRepository.findById(id).map(chatReadMapper::map);
    }

    public List<ChatReadDto> findAllByCommunityId(int id) {
        return chatRepository.findAllByCommunity_Id(id).stream().map(chatReadMapper::map).toList();
    }

    public ChatReadDto save(ChatEditDto chatEditDto) {
        return Optional.of(chatEditDto)
                .map(chatEditMapper::map)
                .map(chatRepository::save)
                .map(chatReadMapper::map).orElseThrow();
    }

    public Optional<ChatReadDto> update(Integer id, ChatEditDto chatEditDto) {
        return chatRepository.findById(id)
                .map(chat -> chatEditMapper.map(chatEditDto, chat))
                .map(chatRepository::saveAndFlush)
                .map(chatReadMapper::map);
    }

    public boolean delete(Integer id) {
        return chatRepository.findById(id)
                .map(chat -> {
                    chatRepository.delete(chat);
                    return true;
                }).orElse(false);
    }
}
