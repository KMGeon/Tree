package com.giggal.board.domain.image.application;


import com.giggal.board.domain.image.dto.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    FileResponse uploadImageToFileSystem(MultipartFile file) throws IOException;
    byte[] downloadImageFromFileSystem(String fileCodeName) throws IOException;
}
