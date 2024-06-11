package com.soCompany.sunflower.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserReadDto {
    private String username;
    private String email;
    private String avatarUrl;
    private BigDecimal balance;
}
