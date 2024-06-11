package com.soCompany.sunflower.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CommunityImageService {
    @Value("${app.images.communities-logotype-directory-path}")
    private String logotypeDirectoryPath;

    @Value("${app.images.communities-banner-directory-path}")
    private String bannerDirectoryPath;

    @SneakyThrows
    public void uploadImage(String directoryPath, String imagePath, InputStream content) {
        Path fullPath = Path.of(directoryPath, imagePath);

        try (content) {
            Files.createDirectories(fullPath.getParent());
            Files.write(fullPath, content.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    public void uploadLogotype(String imagePath, InputStream content) {
        uploadImage(logotypeDirectoryPath, imagePath, content);
    }

    public void uploadBanner(String imagePath, InputStream content) {
        uploadImage(bannerDirectoryPath, imagePath, content);
    }

    @SneakyThrows
    public Optional<byte[]> getImage(String directoryPath, String imagePath) {
        Path fullPath = Path.of(directoryPath, imagePath);
        return Files.exists(fullPath)
                ? Optional.of(Files.readAllBytes(fullPath))
                : Optional.empty();
    }

    public Optional<byte[]> getLogotype(String imagePath) {
        return getImage(logotypeDirectoryPath, imagePath);
    }

    public Optional<byte[]> getBanner(String imagePath) {
        return getImage(bannerDirectoryPath, imagePath);
    }

    @SneakyThrows
    public boolean deleteImage(String directoryPath, String imagePath) {
        Path fullPath = Path.of(directoryPath, imagePath);
        return Files.deleteIfExists(fullPath);
    }

    public boolean deleteLogotype(String imagePath) {
        return deleteImage(logotypeDirectoryPath, imagePath);
    }

    public boolean deleteBanner(String imagePath) {
        return deleteImage(bannerDirectoryPath, imagePath);
    }
}

