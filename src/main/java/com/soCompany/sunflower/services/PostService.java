package com.soCompany.sunflower.services;

import com.soCompany.sunflower.dto.PostEditDto;
import com.soCompany.sunflower.dto.PostReadDto;
import com.soCompany.sunflower.mapper.PostEditMapper;
import com.soCompany.sunflower.mapper.PostReadMapper;
import com.soCompany.sunflower.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostReadMapper postReadMapper;
    private final PostEditMapper postEditMapper;

    public List<PostReadDto> findAll() {
        return postRepository.findAll().stream().map(postReadMapper::map).toList();
    }

    public Optional<PostReadDto> findById(Integer id) {
        return postRepository.findById(id).map(postReadMapper::map);
    }

    public PostReadDto save(PostEditDto postEditDto) {
        return Optional.of(postEditDto)
                .map(postEditMapper::map)
                .map(postRepository::save)
                .map(postReadMapper::map)
                .orElseThrow();
    }

    public Optional<PostReadDto> update(Integer id, PostEditDto postEditDto) {
        return postRepository.findById(id)
                .map(post -> postEditMapper.map(postEditDto, post))
                .map(postRepository::saveAndFlush)
                .map(postReadMapper::map);
    }

    public boolean delete(Integer id) {
        return postRepository.findById(id)
                .map(post -> {
                    postRepository.delete(post);
                    postRepository.flush();
                    return true;
                }).orElse(false);
    }
}
