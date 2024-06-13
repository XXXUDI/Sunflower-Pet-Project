package com.soCompany.sunflower.dto;

import com.soCompany.sunflower.entity.Community;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChatReadDto {
    private int id;
    private String name;
    private Community community;
}
