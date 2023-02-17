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


    @GetMapping(value = "/get-images")
    public ResponseEntity<List<ImagesGroup>> getImages() {
        return new ResponseEntity<>(awss3Service.getImages(), HttpStatus.OK);
    }
}
