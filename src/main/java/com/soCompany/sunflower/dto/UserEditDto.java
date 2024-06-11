package com.soCompany.sunflower.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserEditDto {
    private String username;
    private String password;
    private String email;
    private MultipartFile avatar;
    private BigDecimal balance;
}
