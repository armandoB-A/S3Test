package com.blasarcesh.s3test.controllers;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inicio/")
public class InicioController {

    @GetMapping
    public ResponseEntity<Inicio> getInicio() {
        Inicio inicio = new Inicio();
        inicio.setInic("conexión exitosa");
        return new ResponseEntity<>(inicio, HttpStatus.OK);
    }
    @Data
    class Inicio {
        String inic;
    }
}