package com.blasarcesh.s3test.controllers;


import java.time.LocalDate;
import java.util.List;

import com.blasarcesh.s3test.entities.ImagesGroup;
import com.blasarcesh.s3test.services.ImagesServices;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images/")
public class ImagesController {
    @Autowired
    private ImagesServices awss3Service;

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile file,
                                             @RequestParam(value = "titulo") String titulo,
                                             @RequestParam(value = "fecha")
                                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate fecha) {
        awss3Service.uploadFile(file, titulo, fecha);
        String response = "El archivo " + file.getOriginalFilename() + " fue cargado correctamente a S3";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<String>> listFiles() {
        return new ResponseEntity<List<String>>(awss3Service.getObjectsFromS3(), HttpStatus.OK);
    }

    @GetMapping(value = "/download")
    public ResponseEntity<Resource> download(@RequestParam("key") String key) {
        InputStreamResource resource = new InputStreamResource(awss3Service.downloadFile(key));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"").body(resource);
    }

    @GetMapping(value = "/url")
    public ResponseEntity<String> getUrl(@RequestParam("key") String key) {
        return new ResponseEntity<>(awss3Service.urlFie(key), HttpStatus.OK);
    }

    @GetMapping(value = "/get-images")
    public ResponseEntity<List<ImagesGroup>> getImages() {
        return new ResponseEntity<>(awss3Service.getImages(), HttpStatus.OK);
    }
}
