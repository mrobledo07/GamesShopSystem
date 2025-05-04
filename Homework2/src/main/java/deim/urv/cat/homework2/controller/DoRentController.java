/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.UriRef;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;


/**
 *
 * @author micha
 */

@Controller
@Path("/DoRent")
public class DoRentController {
   
    @Inject CartController cart;
    
    @GET
    @UriRef("do-rent")
    public String doRent(){
        return "cart-list.jsp";
    }
    
    @POST
    @UriRef("add-to-cart")
    public String addToCart(@Valid @BeanParam GameCart g){
        if (cart == null) cart = new CartController();
        cart.addGame(g);
        return "redirect:/GamesList";
    }
}
