package com.guilhermessantos2004.servicearch.service;


import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AnonymousCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;

@Service
public class S3Service {

    private final S3Client s3Client;

    public S3Service() {
        // Cria um cliente S3 anônimo (sem auth)
        this.s3Client = S3Client.builder()
                .credentialsProvider(AnonymousCredentialsProvider.create())
                .region(Region.US_EAST_1) // ajuste se necessário
                .endpointOverride(URI.create("https://s3.amazonaws.com"))
                .build();
    }

    public void uploadFile(String bucketName, String key, File file) {
        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.putObject(putRequest, RequestBody.fromFile(file));
        System.out.println("Arquivo enviado com sucesso!");
    }

    public InputStream downloadFile(String bucketName, String key) {
        GetObjectRequest getRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        return s3Client.getObject(getRequest);
    }

}
