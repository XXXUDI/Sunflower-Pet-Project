package com.soCompany.sunflower.dto;

import com.soCompany.sunflower.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityReadDto implements DtoObject{
    private String name;
    private String description;
    private String logotype;
    private String banner;
}
