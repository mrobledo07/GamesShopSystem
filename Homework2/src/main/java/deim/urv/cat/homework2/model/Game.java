/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

/**
 *
 * @author micha
 */

public class Game {
    private String id;
    private String name;
    private String gameConsole;
    private String availability;
    private String price;
    private String description;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameConsole() {
        return gameConsole;
    }

    public void setGameConsole(String gameConsole) {
        this.gameConsole = gameConsole;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", name=" + name + ", gameConsole=" + gameConsole + ", availability=" + availability + ", price=" + price + ", description=" + description + ", type=" + type + '}';
    }
    
    
}
