/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.mvc.binding.MvcBinding;
import jakarta.ws.rs.FormParam;
import java.io.Serializable;

/**
 *
 * @author micha
 */

@RequestScoped
@Named("gamesFilter")
public class GamesFilter implements Serializable{
    private static final long serialVersionUID = 1L;
   
    @FormParam("Type")
    @MvcBinding
    private String type;
    
    @FormParam("Console")
    @MvcBinding
    private String console;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }
    
}
