package com.soCompany.sunflower.dto;

import com.soCompany.sunflower.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityEditDto implements DtoObject{
    private String name;
    private String description;
    private MultipartFile logotype;
    private MultipartFile banner;
    private User owner;
}
