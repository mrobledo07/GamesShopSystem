/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.Game;
import deim.urv.cat.homework2.model.GamesFilter;
import deim.urv.cat.homework2.service.GamesListService;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.util.List;

/**
 *
 * @author micha
 */

@Controller
@Path("/GamesList")
public class GamesListController {
     // CDI
    @Inject Models models;
    @Inject GamesListService service;

    @GET
    public String showGames() {
        List<Game> games = service.getGames();
        models.put("games", games);
        return "games-list.jsp";
    } 
   
    @POST
    @UriRef("games-list-parameters")
    public String showGamesParameters(@Valid @BeanParam GamesFilter g){
        List<Game> games = service.getFilteredGames(g);
        models.put("games", games);
        return "games-list.jsp";
    }
    
}
