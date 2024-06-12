package com.soCompany.sunflower.rest;

import com.soCompany.sunflower.dto.PostEditDto;
import com.soCompany.sunflower.dto.PostReadDto;
import com.soCompany.sunflower.entity.Post;
import com.soCompany.sunflower.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostRestController {
    private final PostService postService;

    @GetMapping("/{id}")
    public PostReadDto getPostById(@RequestParam Integer id) {
        return postService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody PostEditDto post) {
        PostReadDto result = postService.save(post);
        return "redirect:/" + result.getId();
    }

    @PutMapping("/{id}")
    public PostReadDto updatePost(@RequestParam Integer id, @RequestBody PostEditDto post) {
        return postService.update(id, post).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deletePost(@RequestParam Integer id) {
        if(!postService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
