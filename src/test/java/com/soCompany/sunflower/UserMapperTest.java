package com.soCompany.sunflower;

import com.soCompany.sunflower.dto.UserReadDto;
import com.soCompany.sunflower.entity.User;
import com.soCompany.sunflower.mapper.UserReadMapper;
import com.soCompany.sunflower.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class UserMapperTest {

    @Autowired
    private UserReadMapper userReadMapper;

    @Autowired
    public UserRepository userRepository;

    private User getUser() {
        return User.builder()
                .id(53)
                .username("Maksim")
                .email("MaksimWork@gmail.com")
                .avatar(null)
                .balance(BigDecimal.valueOf(33123L))
                .createdAt(LocalDateTime.of(2002, 12, 27, 20, 22))
                .build();
    }

    @Test
    public void shouldMapUserToUserReadDto() {
        UserReadDto expected = UserReadDto.builder()
                .username("Maksim")
                .email("MaksimWork@gmail.com")
                .avatarUrl(null)
                .balance(BigDecimal.valueOf(33123L))
                .build();

        UserReadDto actual = userReadMapper.map(getUser());

        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getAvatarUrl(), actual.getAvatarUrl());
        assertEquals(expected.getBalance(), actual.getBalance());
    }
}
