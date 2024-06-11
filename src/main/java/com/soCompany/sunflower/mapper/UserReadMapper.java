package com.soCompany.sunflower.mapper;

import com.soCompany.sunflower.dto.UserReadDto;
import com.soCompany.sunflower.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User from) {
        return UserReadDto.builder()
                .id(from.getId())
                .email(from.getEmail())
                .username(from.getUsername())
                .avatarUrl(from.getAvatar())
                .balance(from.getBalance())
                .build();
    }
}
