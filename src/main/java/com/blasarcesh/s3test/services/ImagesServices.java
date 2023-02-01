package com.blasarcesh.s3test.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.blasarcesh.s3test.entities.ImagesGroup;
import com.blasarcesh.s3test.repositories.ImagesGroupRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
public class ImagesServices {
    @Autowired
    private AmazonS3 amazonS3;
    @Autowired
    private ImagesGroupRepository imagesGroupRepository;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public void uploadFile(MultipartFile file, String titulo, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate fecha) {
        File mainFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream stream = new FileOutputStream(mainFile)) {
            stream.write(file.getBytes());
            String newFileName = System.currentTimeMillis() + "_" + mainFile.getName();
            PutObjectRequest request = new PutObjectRequest(bucketName, newFileName, mainFile);
            amazonS3.putObject(request);

            ImagesGroup imagesGroup = new ImagesGroup();
            imagesGroup.setTitulo(titulo);
            imagesGroup.setFecha(fecha);
            imagesGroup.setS3Url(amazonS3.getUrl(bucketName, newFileName).toString());
            imagesGroupRepository.save(imagesGroup);

        } catch (IOException e) {
            System.out.println(e.getMessage() + e);
        }
    }

    public List<String> getObjectsFromS3() {
        ListObjectsV2Result result = amazonS3.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        return objects.stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    public InputStream downloadFile(String key) {
        S3Object object = amazonS3.getObject(bucketName, key);
        return object.getObjectContent();
    }

    public String urlFie(String key) {
        return amazonS3.getUrl(bucketName, key).toString();
    }

    public List<ImagesGroup> getImages() {
        return imagesGroupRepository.findByOrderByFechaDesc();
    }
}