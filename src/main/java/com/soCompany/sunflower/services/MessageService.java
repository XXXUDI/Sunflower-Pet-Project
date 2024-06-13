package com.soCompany.sunflower.services;

import com.soCompany.sunflower.dto.MessageEditDto;
import com.soCompany.sunflower.dto.MessageReadDto;
import com.soCompany.sunflower.mapper.MessageEditMapper;
import com.soCompany.sunflower.mapper.MessageReadMapper;
import com.soCompany.sunflower.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageReadMapper messageReadMapper;
    private final MessageEditMapper messageEditMapper;

    public List<MessageReadDto> getAllMessagesByChatId(Integer chatId) {
        return messageRepository.findAllByChat_Id(chatId).stream().map(messageReadMapper::map).toList();
    }

    public MessageReadDto save(MessageEditDto messageEditDto) {
        return Optional.of(messageEditDto)
                .map(messageEditMapper::map)
                .map(messageRepository::save)
                .map(messageReadMapper::map).orElseThrow();
    }

    public Optional<MessageReadDto> getMessageById(Integer id) {
        return messageRepository.findById(id).map(messageReadMapper::map);
    }

    public Optional<MessageReadDto> update(Integer id, MessageEditDto messageEditDto) {
        return messageRepository.findById(id)
                .map(msg -> messageEditMapper.map(messageEditDto, msg))
                .map(messageRepository::saveAndFlush)
                .map(messageReadMapper::map);
    }

    public boolean delete(Integer id) {
        return messageRepository.findById(id)
                .map(msg -> {
                    messageRepository.delete(msg);
                    return true;
                }).orElse(false);
    }
}
