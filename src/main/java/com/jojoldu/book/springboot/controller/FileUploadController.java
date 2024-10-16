package com.jojoldu.book.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        // 업로드 경로 설정
        String uploadDir = "uploads/";
        String fileName = file.getOriginalFilename();

        // 파일을 업로드 경로에 저장
        File destinationFile = new File(uploadDir + fileName);
        file.transferTo(destinationFile);

        // 썸네일 생성
        createThumbnail(destinationFile);

        return "업로드 성공";
    }

    // 썸네일 생성 메서드
    public void createThumbnail(File imageFile) throws IOException {
        // 썸네일 생성 코드 작성
    }
}
