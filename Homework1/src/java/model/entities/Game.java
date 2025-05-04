/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.List;

/**
 *
 * @author micha
 */
@Entity
@XmlRootElement
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "Game_Gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Game_Gen")
    @Column(name = "game_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Console gameConsole;

    private boolean availability;

    private double price;

    private String description;
    
    @Enumerated(EnumType.STRING)
    private Type type;
    
    @JsonbTransient
    @JoinTable(name="RENTALS_GAMES",
            joinColumns=@JoinColumn(name="GAME_ID", referencedColumnName="game_id"),
            inverseJoinColumns=@JoinColumn(name="RENTAL_ID", referencedColumnName="rental_id"))
    @ManyToMany
    private List<Rental> rentals;
    
    @JsonbTransient
    @JoinTable(name="GAMES_SHOP",
            joinColumns=@JoinColumn(name="GAME_ID", referencedColumnName="game_id"),
            inverseJoinColumns=@JoinColumn(name="SHOP_ID", referencedColumnName="shop_id"))
    @ManyToMany
    private List<Shop> shops;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Console getGameConsole() {
        return gameConsole;
    }

    public void setGameConsole(Console gameConsole) {
        this.gameConsole = gameConsole;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gameConsole=" + gameConsole +
                ", availability=" + availability +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", shopAddress=" + shops +
                '}';
    }
}

