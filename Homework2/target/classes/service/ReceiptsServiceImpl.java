/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author micha
 */
public class ReceiptsServiceImpl implements ReceiptsService{
    
    private final WebTarget webTarget;
    private static final String BASE_URI = "http://localhost:8080/Homework1/webresources";
    private final Client client;
    
    public ReceiptsServiceImpl() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("rentals");
    }
    
    @Override
    public List<String> getReceipts(int id){
        Response response = webTarget
                .queryParam("id", id)
                .request(MediaType.APPLICATION_JSON)
                .get();
        
        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<String>>() {});
        }
        return null;
    }

}
