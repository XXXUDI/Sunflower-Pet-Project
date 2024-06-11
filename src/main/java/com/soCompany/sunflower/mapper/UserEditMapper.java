package com.soCompany.sunflower.mapper;

import com.soCompany.sunflower.dto.UserEditDto;

import com.soCompany.sunflower.entity.User;

public class UserEditMapper implements Mapper<User, UserEditDto> {
    @Override
    public UserEditDto map(User from) {
        return null;
    }

    @Override
    public UserEditDto map(User object, UserEditDto toObject) {
        return Mapper.super.map(object, toObject);
    }
}
