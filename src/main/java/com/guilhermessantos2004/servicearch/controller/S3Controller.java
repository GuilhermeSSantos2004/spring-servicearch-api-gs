package com.guilhermessantos2004.servicearch.controller;
import com.guilhermessantos2004.servicearch.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        String bucketName = "481665112140-app-persons";
        String key = "imagens/" + file.getOriginalFilename();

        File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile);

        s3Service.uploadFile(bucketName, key, tempFile);

        tempFile.delete();

        return "Upload concluído para: " + key;
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam String key) throws IOException {
        String bucketName = "481665112140-app-persons";

        InputStream inputStream = s3Service.downloadFile(bucketName, key);
        byte[] bytes = inputStream.readAllBytes();
        inputStream.close();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(bytes);
    }
}
