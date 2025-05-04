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
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

/**
 *
 * @author micha
 */
@Controller
@Path("GameInfo")
public class GameInfoController {
    
    @Inject GameCart game;
    
    @POST
    @UriRef("get-game-info")
    public String getInfoGame (@Valid @BeanParam GameCart game){
        this.game = game;
        return "game-info.jsp";
    }
}
