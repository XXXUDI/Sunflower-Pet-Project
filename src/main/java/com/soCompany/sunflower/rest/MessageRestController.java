package com.soCompany.sunflower.rest;

import com.soCompany.sunflower.dto.MessageEditDto;
import com.soCompany.sunflower.dto.MessageReadDto;
import com.soCompany.sunflower.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v3/msg")
@RequiredArgsConstructor
public class MessageRestController {
    private final MessageService messageService;

    @GetMapping("/chat/{id}")
    public List<MessageReadDto> getMessagesByChatId(@RequestParam int id) {
        return messageService.getAllMessagesByChatId(id);
    }

    @GetMapping("/{id}")
    public MessageReadDto getMessageById(@RequestParam int id) {
        return messageService.getMessageById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MessageReadDto createMessage(@RequestBody MessageEditDto message) {
        return messageService.save(message);
    }

    @PutMapping("/{id}")
    public MessageReadDto updateMessage(@RequestParam int id, @RequestBody MessageEditDto message) {
        return messageService.update(id, message)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public void deleteMessage(@RequestParam int id) {
        if(!messageService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
