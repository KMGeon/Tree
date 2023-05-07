package com.giggal.board.domain.image.application.Impl;

import com.giggal.board.common.FileUtils;
import com.giggal.board.domain.image.application.FileService;
import com.giggal.board.domain.image.dto.FileResponse;
import com.giggal.board.domain.image.entity.FileData;
import com.giggal.board.domain.image.repository.FileDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private static final String FOLDER_PATH = "C:\\Users\\pos04\\IdeaProjects\\board\\src\\main\\resources\\file\\";
    private final FileDataRepository storageRepository;

    public FileServiceImpl(FileDataRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @Override
    @Transactional
    public FileResponse uploadImageToFileSystem(MultipartFile file) throws IOException {
        String fileCodeName = FileUtils.getFileCodeName(file);
        log.info("upload fileCodeName: {}", fileCodeName);
        String filePath = FOLDER_PATH + fileCodeName;

        FileData fileData = storageRepository.save(
                FileData.builder()
                        .name(fileCodeName)
                        .type(file.getContentType())
                        .size(file.getSize())
                        .filePath(filePath)
                        .build()
        );
        file.transferTo(new File(filePath));
        return FileResponse.of(fileData);
    }

    @Override
    @Transactional
    public byte[] downloadImageFromFileSystem(String fileCodeName) throws IOException {
        FileData fileData = storageRepository.findByName(fileCodeName)
                .orElseThrow(() -> new RuntimeException("이미지 실패"));

        String filePath = fileData.getFilePath();
        log.info("download filePath: {}", filePath);
        return Files.readAllBytes(new File(filePath).toPath());
    }
}
