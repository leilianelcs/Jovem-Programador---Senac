package com.veiculo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veiculo.dto.FabricanteDTO;
import com.veiculo.service.FabricanteService;

@RestController
@RequestMapping("/api/fabricantes")
public class FabricanteController {

    @Autowired
    private FabricanteService service;

    // private final FabricanteService service;

    // public FabricanteController(FabricanteService service) {
    // this.service = service;
    // }

    // @GetMapping
    // public List<FabricanteDTO> listar(){
    // this.service = service;
    // }

    @PostMapping
    public ResponseEntity<FabricanteDTO> criar(@RequestBody FabricanteDTO dto) {
        FabricanteDTO criado = service.criar(dto);
        /*
         * URI location =
         * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
         * (criado.getId())
         * .toUri();
         * return ResponseEntity.created(location).body(criado);
         */
        return ResponseEntity.created(null).body(criado);

    }

}
