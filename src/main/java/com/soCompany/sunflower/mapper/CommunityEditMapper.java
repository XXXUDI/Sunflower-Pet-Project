package com.soCompany.sunflower.mapper;

import com.soCompany.sunflower.dto.CommunityEditDto;
import com.soCompany.sunflower.entity.Community;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class CommunityEditMapper implements Mapper<CommunityEditDto, Community>{
    @Override
    public Community map(CommunityEditDto from) {
        return Community.builder()
                .name(from.getName())
                .description(from.getDescription())
                .logotype(from.getLogotype() != null && !from.getLogotype().isEmpty() ? from.getLogotype().getOriginalFilename() : null)
                .banner(from.getBanner() != null && !from.getBanner().isEmpty() ? from.getLogotype().getOriginalFilename() : null)
                .owner(from.getOwner())
                .build();
    }

    @Override
    public Community map(CommunityEditDto object, Community toObject) {
        toObject.setName(object.getName());
        toObject.setDescription(object.getDescription());
        Optional.ofNullable(object.getLogotype())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> toObject.setLogotype(image.getOriginalFilename()));
        Optional.ofNullable(object.getBanner())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> toObject.setBanner(image.getOriginalFilename()));
        toObject.setOwner(object.getOwner());
        return toObject;
    }
}
