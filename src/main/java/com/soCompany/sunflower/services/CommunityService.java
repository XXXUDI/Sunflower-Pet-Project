package com.soCompany.sunflower.services;

import com.soCompany.sunflower.dto.CommunityEditDto;
import com.soCompany.sunflower.dto.CommunityReadDto;
import com.soCompany.sunflower.entity.Community;
import com.soCompany.sunflower.mapper.CommunityEditMapper;
import com.soCompany.sunflower.mapper.CommunityReadMapper;
import com.soCompany.sunflower.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final CommunityReadMapper communityReadMapper;
    private final CommunityEditMapper communityEditMapper;
    private final CommunityImageService imageService;

    public List<CommunityReadDto> findAllCommunities() {
        return communityRepository.findAll().stream().map(communityReadMapper::map).toList();
    }

    public Optional<CommunityReadDto> findCommunityById(int id) {
        return communityRepository.findById(id).map(communityReadMapper::map);
    }

    public Optional<CommunityReadDto> findCommunityByName(String name) {
        return communityRepository.findByName(name).map(communityReadMapper::map);
    }

    public CommunityReadDto save(CommunityEditDto communityEditDto) {
        return Optional.of(communityEditDto)
                .map(dto -> {
                    uploadLogotype(dto.getLogotype());
                    uploadBanner(dto.getBanner());
                    return communityEditMapper.map(dto);
                })
                .map(communityRepository::save)
                .map(communityReadMapper::map)
                .orElseThrow();
    }

    public Optional<CommunityReadDto> update(Integer id, CommunityEditDto communityEditDto) {
        return communityRepository.findById(id)
                .map(entity -> {
                    deleteLogotype(entity.getLogotype());
                    deleteBanner(entity.getBanner());
                    uploadLogotype(communityEditDto.getLogotype());
                    uploadBanner(communityEditDto.getBanner());
                    return communityEditMapper.map(communityEditDto, entity);
                })
                .map(communityRepository::saveAndFlush)
                .map(communityReadMapper::map);
    }

    public boolean delete(Integer id) {
        return communityRepository.findById(id)
                .map(community -> {
                    deleteLogotype(community.getLogotype());
                    communityRepository.delete(community);
                    communityRepository.flush();
                    return true; }
                ).orElse(false);
    }

    @SneakyThrows
    private void uploadLogotype(MultipartFile image) {
        if(image != null && !image.isEmpty()) {
            imageService.uploadLogotype(image.getOriginalFilename(), image.getInputStream());
        }
    }

    @SneakyThrows
    private void uploadBanner(MultipartFile image) {
        if(image != null && !image.isEmpty()) {
            imageService.uploadBanner(image.getOriginalFilename(), image.getInputStream());
        }
    }

    @SneakyThrows
    private void deleteLogotype(String image) {
        if(hasText(image)) {
            imageService.deleteLogotype(image);
        }
    }

    @SneakyThrows
    private void deleteBanner(String image) {
        if(hasText(image)) {
            imageService.deleteBanner(image);
        }
    }

    public Optional<byte[]> findLogotype(Integer id) {
        return communityRepository.findById(id)
                .map(Community::getLogotype)
                .filter(StringUtils::hasText)
                .flatMap(imageService::getLogotype);
    }

    public Optional<byte[]> findBanner(Integer id) {
        return communityRepository.findById(id)
                .map(Community::getBanner)
                .filter(StringUtils::hasText)
                .flatMap(imageService::getBanner);
    }


}
