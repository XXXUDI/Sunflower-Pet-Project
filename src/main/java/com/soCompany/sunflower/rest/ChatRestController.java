package com.soCompany.sunflower.rest;

import com.soCompany.sunflower.dto.ChatEditDto;
import com.soCompany.sunflower.dto.ChatReadDto;
import com.soCompany.sunflower.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v3/chat")
@RequiredArgsConstructor
public class ChatRestController {
    private final ChatService chatService;

    @GetMapping("/community/{id}")
    public List<ChatReadDto> getCommunityChats(@RequestParam Integer id) {
        return chatService.findAllByCommunityId(id);
    }

    @GetMapping("/{id}")
    public ChatReadDto getChat(@RequestParam Integer id) {
        return chatService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createChat(@RequestBody ChatEditDto chatEditDto) {
        ChatReadDto chatReadDto = chatService.save(chatEditDto);
        return "redirect:/ " + chatReadDto.getId();
    }

    @PutMapping("/{id}")
    public ChatReadDto updateChat(@PathVariable Integer id, @RequestBody ChatEditDto chatEditDto) {
        return chatService.update(id, chatEditDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable Integer id) {
        if(!chatService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
