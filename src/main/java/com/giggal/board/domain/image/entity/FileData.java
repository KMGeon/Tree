package com.giggal.board.domain.image.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filedata_id")
    private Long id;

    private String name;

    private String type;

    private long size;

    private String filePath;

    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;

    @Builder
    public FileData(
            String name,
            String type,
            long size,
            String filePath,
            byte[] imageData
    ) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.filePath = filePath;
        this.imageData = imageData;
    }
}
