package com.giggal.board.domain.image.dto;

import com.giggal.board.domain.image.entity.FileData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponse {
    private String fileName;
    private String downloadUri;

    private String type;
    private long size;
    private byte[] imageData;

    public static FileResponse of(FileData fileData) {
        return FileResponse.builder()
                .fileName(fileData.getName())
                .type(fileData.getType())
                .size(fileData.getSize())
                .downloadUri(fileData.getFilePath())
                .imageData(fileData.getImageData())
                .build();
    }
}