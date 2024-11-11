package com.example.api_java.servico;
import com.example.api_java.entity.Destino;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class DestinoServico {
    private List<Destino> destinos = new ArrayList();
    public List<Destino> getAllDestinos(){
        return this.destinos;
    }

    public Destino getDestinoById (Long id){
        Destino destino = destinos.stream().filter(t -> t.getId() == id).findFirst().get();
        return destino;
    }

    public Destino createDestino(Destino destino){
        destinos.add(destino);
        return destino;
    }

    public Destino updateDestino(Long id, Destino destino){
        this.destinos = destinos.stream().map(t ->{
            if (t.getId() == id){
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

}