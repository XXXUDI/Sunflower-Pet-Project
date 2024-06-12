package com.soCompany.sunflower.dto;

import com.soCompany.sunflower.entity.Community;
import com.soCompany.sunflower.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostEditDto implements DtoObject{
    private String title;
    private String content;
    private User author;
    private Community community;

}
