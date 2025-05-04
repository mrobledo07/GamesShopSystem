/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author micha
 */
@Named("cart")
@SessionScoped
public class CartController implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<GameCart> games;
    private int nGames;

    public int getnGames() {
        return nGames;
    }

    public void setnGames(int nGames) {
        this.nGames = nGames;
    }
        
    public CartController(){
        this.games = new ArrayList<>();
    }
    
    public void addGame(GameCart g){
       this.games.add(g);
       this.nGames++;
    }
    
    public void removeGame(int gameId){
        int index = 0;
        for (GameCart g : this.games){
            if (Integer.parseInt(g.getId()) == gameId){
                this.games.remove(index);
                break;
            }
            index++;
        }
        
        this.nGames--;
    }
    
    public void clearCart(){
        this.games.clear();
        this.nGames = 0;
    }
    
    public List<GameCart> getGames() {
        return games;
    }

    public void setGames(List<GameCart> games) {
        this.games = games;
    }
}