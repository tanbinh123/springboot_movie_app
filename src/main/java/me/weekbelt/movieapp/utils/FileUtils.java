package me.weekbelt.movieapp.utils;

import me.weekbelt.movieapp.domain.fileInfo.FileInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.UUID;

@Component
public class FileUtils {

    @Value("${property.image.url}")
    private String IMAGE_PATH;
    @Value("${property.image.uploadImageFolder}")
    private String UPLOAD_IMAGE;

    public FileInfo saveFileAtStorage(MultipartFile multipartFile) {
        String contentType = multipartFile.getContentType();
        String fileName = makeInherenceFile(multipartFile.getOriginalFilename());
        String saveFileName = UPLOAD_IMAGE + fileName;

        saveFile(multipartFile, IMAGE_PATH + saveFileName);

        return FileInfo.builder()
                .contentType(contentType)
                .fileName(fileName)
                .saveFileName(saveFileName)
                .build();
    }

    private String makeInherenceFile(String originalName) {
        UUID uuid = UUID.randomUUID();
        return uuid.toString() + "_" + originalName;
    }

    private void saveFile(MultipartFile multipartFile, String saveAddr) {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(saveAddr));
             BufferedInputStream bufferedInputStream = new BufferedInputStream(multipartFile.getInputStream())) {

            int readCount;
            byte[] buffer = new byte[1024];

            while ((readCount = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, readCount);
            }

        } catch (Exception e) {
            throw new RuntimeException("File Save Error");
        }

    }
}
