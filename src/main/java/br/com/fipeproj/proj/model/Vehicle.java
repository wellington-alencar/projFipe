package br.com.fipeproj.proj.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vehicle(@JsonAlias("Valor") String valor,
                      @JsonAlias("Marca") String marca,
                      @JsonAlias("Modelo") String modelo,
                      @JsonAlias("AnoModelo") Integer anoModelo,
                      @JsonAlias("Combustivel") String combustivel) {

    @Override
    public String toString() {
        return "Modelo: " + modelo+"\n"
                +"Marca: " + marca+"\n"
                +"Valor: " + valor+"\n"
                +"Ano do modelo: " + anoModelo+"\n"
                +"Combustivel: "+ combustivel+"\n";
    }
}
