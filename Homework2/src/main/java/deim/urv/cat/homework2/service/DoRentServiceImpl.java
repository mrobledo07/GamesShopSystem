/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;


import deim.urv.cat.homework2.model.Rental;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;

/**
 *
 * @author micha
 */
public class DoRentServiceImpl implements DoRentService {
    private final WebTarget webTarget;
    private static final String BASE_URI = "http://localhost:8080/Homework1/webresources";
    private final Client client;
    
    public DoRentServiceImpl() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("rentals");
    }
        
    @Override
    public Map doRent(Rental rental, String credentials) {
        Response response = webTarget
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", credentials)
                .post(Entity.entity(rental, MediaType.APPLICATION_JSON));
                
        if (response.getStatus() == 200) {
            return response.readEntity(Map.class); 
        } else {
            return null;
        }   
    }
}
