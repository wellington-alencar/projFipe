package br.com.fipeproj.proj.controller;

import br.com.fipeproj.proj.model.Data;
import br.com.fipeproj.proj.model.Modelos;
import br.com.fipeproj.proj.model.Vehicle;
import br.com.fipeproj.proj.service.ConsumerAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.fipeproj.proj.model.Marcas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Controller {
    private String API = "https://parallelum.com.br/fipe/api/v1/";
    private String MARCAS = "/marcas/";
    public void menu (){

        ObjectMapper objectMapper = new ObjectMapper();
        Scanner sc = new Scanner(System.in);
        ConsumerAPI consumerAPI = new ConsumerAPI();
        List<Vehicle> vehicleList = new ArrayList<>();
        List<Marcas> marcasList = null;
        System.out.println("Digite o tipo do veiculo ");
        System.out.println("Carros");
        System.out.println("Caminhoes");
        System.out.println("motos");
        String type = sc.next().toLowerCase();

        String json = consumerAPI.getData(API + type+ "/marcas/");
        try {
            marcasList = objectMapper.readValue(json, new TypeReference<List<Marcas>>(){});
            marcasList.forEach(System.out::println);
            System.out.println("Digite o codigo da marca desejada: ");
            var codigo = sc.next();
            sc.nextLine();
            json = consumerAPI.getData(API+type+"/marcas/"+codigo+"/modelos");
            System.out.println(json);
            var modelos = objectMapper.readValue(json, Modelos.class);

            System.out.println("Todos os modelos dessa Marca");
            modelos.modelos().stream().forEach(System.out::println);

            System.out.println("Digite o nome do Veiculo que deseja consultar: ");
            var nameVehicle = sc.nextLine();

            List<Data> filteredModels = modelos.modelos().stream()
                    .filter(m-> m.nome().toLowerCase().contains(nameVehicle.toLowerCase()))
                    .collect(Collectors.toList());
            System.out.println("Modelos filtrados");
            filteredModels.forEach(System.out::println);
            System.out.println("Digite o codigo do modelo: ");
            var cod = sc.next();
            json = consumerAPI.getData(API+type+"/marcas/"+codigo+"/modelos/"+cod+"/anos");

            List<Data> years = objectMapper.readValue(json, new TypeReference<List<Data>>(){});
            for (int i = 0; i < years.size(); i++){
                json = consumerAPI.getData(API+type+"/marcas/"+codigo+"/modelos/"+cod+"/anos"+"/"
                        +years.get(i).codigo());
                Vehicle vehicle = objectMapper.readValue(json, Vehicle.class);
                vehicleList.add(vehicle);
            }
            vehicleList.forEach(System.out::println);


        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
