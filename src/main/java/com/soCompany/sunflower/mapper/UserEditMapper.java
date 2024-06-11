package com.soCompany.sunflower.mapper;

import com.soCompany.sunflower.dto.UserEditDto;

import com.soCompany.sunflower.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Predicate;

/* This class is using to convert UserEditDto to User
(Because Repository works only with default user, and we have to convert Dto -> User)
 */
@Component
public class UserEditMapper implements Mapper<UserEditDto, User> {

    @Override
    public User map(UserEditDto from) {
        return User.builder()
                .username(from.getUsername())
                .password(from.getPassword())
                .email(from.getEmail())
                .balance(from.getBalance())
                .avatar(from.getAvatar().isEmpty() ? from.getAvatar().getOriginalFilename() : null)
                .build();
    }

    // For update methods
    @Override
    public User map(UserEditDto object, User toObject) {
        toObject.setUsername(object.getUsername());
        toObject.setPassword(object.getPassword());
        toObject.setEmail(object.getEmail());
        toObject.setBalance(object.getBalance());

        Optional.ofNullable(object.getAvatar())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> toObject.setAvatar(image.getOriginalFilename()));

        return toObject;
    }

}
