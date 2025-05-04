/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.Game;
import deim.urv.cat.homework2.model.GamesFilter;

import java.util.List;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


/**
 *
 * @author micha
 */
public class GamesListServiceImpl implements GamesListService {
    
    private final WebTarget webTarget;
    private static final String BASE_URI = "http://localhost:8080/Homework1/webresources";
    private final Client client;
    
    public GamesListServiceImpl() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("games");
    }
    
    @Override
    public List<Game> getGames(){
        Response response = webTarget
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            return response.readEntity(List.class);
        }
        return null;
    }
    
    @Override
    public List<Game> getFilteredGames(GamesFilter g){
        Response response = webTarget
                .queryParam("type", g.getType())
                .queryParam("console", g.getConsole())
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            return response.readEntity(List.class);
        }
        return null;
    }
}
