package br.com.fipeproj.proj.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelos(List<Data> modelos) {
}
