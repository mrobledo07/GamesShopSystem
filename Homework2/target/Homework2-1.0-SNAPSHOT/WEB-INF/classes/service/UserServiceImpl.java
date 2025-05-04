package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.controller.UserForm;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

        
public class UserServiceImpl implements UserService {
    private final WebTarget webTarget;
    private final jakarta.ws.rs.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/Homework1/webresources";
    
    public UserServiceImpl() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("customers");
    }
    
    @Override
    public int verifyUser(UserForm user) {
        Response response = webTarget
            .queryParam("user", user.getUsername())
            .queryParam("password", user.getPassword())
            .request()
            .get();
        
        if (response.getStatus() == 200)
            return response.readEntity(Integer.class);
        else
            return 0;
    }

}
