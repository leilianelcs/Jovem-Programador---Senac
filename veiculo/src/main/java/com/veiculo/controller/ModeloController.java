package com.veiculo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veiculo.dto.ModeloDTO;
import com.veiculo.service.ModeloService;

@RestController
@RequestMapping("/api/modelos")
public class ModeloController {

    @Autowired
    private ModeloService service;

    @PostMapping
    public ResponseEntity<ModeloDTO> criar(@RequestBody ModeloDTO dto) {
        ModeloDTO criado = service.criar(dto);

        return ResponseEntity.created(null).body(criado);

    }

}
