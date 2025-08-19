package com.veiculo.controller;

import java.net.URL;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.veiculo.dto.FabricanteDTO;

@RestController
@RequestMapping("/api/fabricantes")
public class FabricanteController {
    // private final FabricanteService service;
    @PostMapping
    public ResponseEntity<FabricanteDTO> criar(@RequestBody FabricanteDTO dto) {
        // FabricanteDTO criado = service.criar(dto);
        // URL location =
        // ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(criado.getId())
        // .toUri();
        return ResponseEntity.created(location).body(criado);

    }

}
