package com.example.api_java.controller;

import com.example.api_java.entity.Destino;
import com.example.api_java.servico.DestinoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/destinos")
public class DestinoController {

    @Autowired
    private DestinoServico destinoServico;

    @GetMapping
    public ResponseEntity<List<Destino>> getAllDestinos() {
        List<Destino> destinos = destinoServico.getAllDestinos();
        return new ResponseEntity<>(destinos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Destino> createDestino(@RequestBody Destino destino) {
        destinoServico.createDestino(destino);
        return new ResponseEntity<>(destino, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> getDestinoById(@PathVariable long id) {
        Destino destino = destinoServico.getDestinoById(id);
        return new ResponseEntity<>(destino, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destino> updateDestino(@PathVariable long id, @RequestBody Destino destino) {
        destinoServico.updateDestino(id, destino);
        return new ResponseEntity<>(destino, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestino(@PathVariable long id) {
        destinoServico.deleteDestino(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<List<Destino>> pesquisarDestinos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String localizacao) {

        List<Destino> destinos = destinoServico.pesquisarDestinos(nome, localizacao);

        if (!destinos.isEmpty()) {
            return new ResponseEntity<>(destinos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/detalhes")

    public ResponseEntity<List<Destino>> detalhesPorDescricao(@RequestParam String descricao) {
        List<Destino> destinos = destinoServico.pesquisarDestinosPorDescricao(descricao);
        if (!destinos.isEmpty()) {
            return new ResponseEntity<>(destinos, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping  ("/{id}/avaliacoes")
    public ResponseEntity<Destino> avaliarDestino(@PathVariable Long id, @RequestParam int nota) {
        if (nota < 1 || nota > 10) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Destino> destino = destinoServico.avaliarDestino(id, nota);
        return destino.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() ->
                        new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}















