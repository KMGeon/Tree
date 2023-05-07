package com.giggal.board.domain.image.application.Impl;

import com.giggal.board.domain.image.application.FileService;
import com.giggal.board.domain.image.dto.FileResponse;
import com.giggal.board.domain.image.entity.FileData;
import com.giggal.board.domain.image.repository.FileDataRepository;
import com.giggal.board.common.GeoReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@Transactional
@SpringBootTest
@ActiveProfiles({"local"})
class FileServiceImplTest {

    private static final String FOLDER_PATH = "C:\\Users\\pos04\\IdeaProjects\\board\\src\\main\\resources\\file\\";

    @Autowired
    private FileService fileService;

    @Autowired
    private FileDataRepository fileDataRepository;

    private GeoReader geoIp2Mock;

    @BeforeEach
    void setUp() throws IOException {
        geoIp2Mock = new GeoReader();
    }

    @Test
    @DisplayName("파일 Upload")
    void uploadImageToFileSystem() throws Exception {
        // given
        String fileName = "test.jpg";
        String fileType = "image/jpeg";
        long fileSize = 12L;
        byte[] fileContent = "test content".getBytes();
        MultipartFile file = new MockMultipartFile(
                fileName,
                fileName,
                fileType,
                fileContent
        );

        // when
        FileResponse fileResponse = fileService.uploadImageToFileSystem(file);

        // then
        FileData savedFileData = fileDataRepository.findById(1L).orElseThrow();

        assertThat(savedFileData.getType()).isEqualTo(fileType);
        assertThat(savedFileData.getSize()).isEqualTo(fileSize);
        assertThat(savedFileData.getFilePath()).contains(fileName);

        byte[] downloadedFileContent = fileService.downloadImageFromFileSystem(savedFileData.getName());
        assertThat(downloadedFileContent).isEqualTo(fileContent);
    }


}