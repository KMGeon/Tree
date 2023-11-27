package com.giggal.board.common;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

    public static String getFileCodeName(MultipartFile file) {
        return RandomStringUtils.randomAlphanumeric(8) + "-" + file.getOriginalFilename();
    }
}