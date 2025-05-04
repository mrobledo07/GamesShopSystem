/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.Customer;
import deim.urv.cat.homework2.model.Game;
import deim.urv.cat.homework2.model.Rental;
import deim.urv.cat.homework2.service.DoRentService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.UriRef;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import java.util.Map;

/**
 *
 * @author micha
 */

@Controller
@Path("/PerformRent")
public class PerformRentController {
    @Inject CartController cart;
    @Inject DoRentService service;

    @Context
    private HttpServletRequest request;
    
    @GET
    @UriRef("perform-rent")
    public String performRent(){
        HttpSession session = request.getSession();
        UserForm user = (UserForm)session.getAttribute("user");
        if (user == null || user.getUsername() == null || user.getUsername().equals("")){
            return "redirect:/LogIn";
        }
        
        Rental rental = new Rental();
        Customer customer = new Customer();
        customer.setId(user.getId());
        rental.setCustomer(customer);
        
        for (GameCart game : cart.getGames()) {
            Game g = new Game();
            g.setId(game.getId());
            rental.getGames().add(g);
        }
        
        String credentials = (String)session.getAttribute("credentials");
        Map result = service.doRent(rental, credentials);
        if (result == null)
            return "redirect:/GamesList";
        
        cart.clearCart();
        return "redirect:/GamesList";
    }
}
