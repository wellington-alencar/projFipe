package br.com.fipeproj.proj.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumerAPI {
    public String getData(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = null;
        try{
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch (IOException e){
            throw new RuntimeException();
        }catch (InterruptedException e){
            throw new RuntimeException();
        }
        String json = response.body();
        return json;

    }
}
