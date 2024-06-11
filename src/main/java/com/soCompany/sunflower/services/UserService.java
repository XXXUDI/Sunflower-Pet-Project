package com.soCompany.sunflower.services;

import com.soCompany.sunflower.dto.UserEditDto;
import com.soCompany.sunflower.dto.UserReadDto;
import com.soCompany.sunflower.entity.User;
import com.soCompany.sunflower.mapper.UserEditMapper;
import com.soCompany.sunflower.mapper.UserReadMapper;
import com.soCompany.sunflower.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserReadMapper userReadMapper;
    private final UserEditMapper userEditMapper;
    private final UserImageService imageService;

    public Optional<UserReadDto> findById(int id) {
        return userRepository.findById(id).map(userReadMapper::map);
    }

    public Optional<UserReadDto> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userReadMapper::map);
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream().map(userReadMapper::map).toList();
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if(image != null && !image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    @SneakyThrows
    private void deleteImage(String image) {
        if(hasText(image)) {
            imageService.delete(image);
        }
    }

    public Optional<byte[]> findAvatar(Integer id) {
        return userRepository.findById(id)
                .map(User::getAvatar)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
    }

    public UserReadDto save(UserEditDto userEditDto) {
        return Optional.of(userEditDto)
                .map(Dto -> {
                    uploadImage(userEditDto.getAvatar());
                    return userEditMapper.map(userEditDto);
                })
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    public Optional<UserReadDto> update(Integer id, UserEditDto userEditDto) {
        return userRepository.findById(id)
                .map(entity -> {
                    deleteImage(entity.getAvatar());
                    uploadImage(userEditDto.getAvatar());
                    return userEditMapper.map(userEditDto, entity);
                })
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    public boolean delete(Integer id) {
        return userRepository.findById(id)
                .map(employee -> {
                    deleteImage(employee.getAvatar());
                    userRepository.delete(employee);
                    userRepository.flush();
                    return true; }
                ).orElse(false);
    }

}
