package br.com.fipeproj.proj.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Marcas(@JsonAlias("codigo") String codigo,
                     @JsonAlias("nome") String nome) {
}
