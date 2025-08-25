package com.veiculo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veiculo.dto.VeiculoDTO;
import com.veiculo.service.VeiculoService;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @PostMapping
    public ResponseEntity<VeiculoDTO> criar(@RequestBody VeiculoDTO dto) {
        VeiculoDTO criado = service.criar(dto);

        return ResponseEntity.created(null).body(criado);

    }

}
