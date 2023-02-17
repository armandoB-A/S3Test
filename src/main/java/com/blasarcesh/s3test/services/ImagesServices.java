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


@Service
public class ImagesServices {
    @Autowired
    private ImagesGroupRepository imagesGroupRepository;



    public List<ImagesGroup> getImages() {
        return imagesGroupRepository.findByOrderByFechaDesc();
    }
}