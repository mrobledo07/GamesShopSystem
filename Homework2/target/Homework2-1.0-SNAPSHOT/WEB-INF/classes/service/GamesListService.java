/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.Game;
import deim.urv.cat.homework2.model.GamesFilter;

import java.util.List;



/**
 *
 * @author micha
 */
public interface GamesListService {
    public List<Game> getGames();
    public List<Game> getFilteredGames(GamesFilter g);

}
