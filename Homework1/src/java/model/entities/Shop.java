/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author micha
 */
@Entity
@XmlRootElement
public class Shop implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "Shop_Gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Shop_Gen")
    @Column(name = "shop_id")
    private Long id;
    
    private String storeName;
    private String street;
    private String number;
    private String city;
    private String postalCode;
    
    @JsonbTransient
    @ManyToMany(mappedBy="shops")
    private List<Game> games;

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getStoreName(){
        return this.storeName;
    }
    
    public void setStoreName(String store){
        this.storeName = store;
    }
    
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public List<Game> getGames(){
        return this.games;
    }
    
    public void setGames(List<Game> games){
        this.games = games;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Shop)) {
            return false;
        }
        Shop other = (Shop) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Shop{" +
               "store='" + storeName + '\'' +
               "street='" + street + '\'' +
               ", number='" + number + '\'' +
               ", city='" + city + '\'' +
               ", postalCode='" + postalCode + '\'' +
               '}';
    }
}


