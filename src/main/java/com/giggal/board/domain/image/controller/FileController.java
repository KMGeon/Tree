package com.giggal.board.domain.image.controller;

import com.giggal.board.domain.image.application.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // 업로드
    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.uploadImageToFileSystem(file));
    }

    // 다운로드
    @GetMapping("/download/{fileCodeName}")
    public ResponseEntity<?> downloadImage(
            @PathVariable("fileCodeName") String fileCodeName
    ) throws IOException {
        log.info("file download fileCodeName: {}", fileCodeName);
        byte[] downloadImage = fileService.downloadImageFromFileSystem(fileCodeName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }
}
