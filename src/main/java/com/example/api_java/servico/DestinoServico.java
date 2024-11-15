package com.example.api_java.servico;

import com.example.api_java.entity.Destino;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class DestinoServico {
    private List<Destino> destinos = new ArrayList<>();

    public List<Destino> getAllDestinos() {
        return this.destinos;
    }

    public Destino getDestinoById(Long id) {
        Destino destino = destinos.stream().filter(t -> t.getId() == id).findFirst().get();
        return destino;
    }

    public Destino createDestino(Destino destino) {
        destinos.add(destino);
        return destino;
    }

    public Destino updateDestino(Long id, Destino destino) {
        this.destinos = destinos.stream().map(t -> {
            if (t.getId() == id) {
                return destino;
            }
            return t;
        }).collect(Collectors.toList());
        return destino;
    }

    public void deleteDestino(Long id) {
        destinos.removeIf(t -> t.getId() == id);
    }

    public List<Destino> pesquisarDestinos(String nome, String localizacao) {
        return destinos.stream()
                .filter(destino ->
                        (nome == null || destino.getNome().equalsIgnoreCase(nome)) &&
                                (localizacao == null || destino.getLocalizacao().equalsIgnoreCase(localizacao))
                )
                .collect(Collectors.toList());
    }


    public List<Destino> pesquisarDestinosPorDescricao(String descricao) {
        return destinos.stream()
                .filter(destino -> destino.getDescricao() != null && destino.getDescricao().equalsIgnoreCase(descricao))
                .collect(Collectors.toList());
    }

    public Optional<Destino> avaliarDestino(Long id, int nota) {
        // Verifica se o destino existe
        Optional<Destino> destinoOptional = Optional.ofNullable(getDestinoById(id));
        destinoOptional.ifPresent(destino -> destino.adicionarAvaliacao(nota)); // Adiciona a avaliação
        return destinoOptional; // Retorna o Optional com o destino atualizado (ou vazio, se não existir)
    }


}
