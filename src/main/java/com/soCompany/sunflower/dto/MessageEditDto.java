package com.soCompany.sunflower.dto;

import com.soCompany.sunflower.entity.Chat;
import com.soCompany.sunflower.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageEditDto {
    private Chat chat;
    private String content;
    private User user;
}
