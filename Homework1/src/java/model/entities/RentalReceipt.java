/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.Embeddable;
import java.util.Date;

/**
 *
 * @author micha
 */
@Embeddable
public class RentalReceipt {
    private Long idReceipt;
    
    private double price;

    private Date returnDate;

    public RentalReceipt() {}
    public RentalReceipt(double price, Date returnDate) {
        this.price = price;
        this.returnDate = returnDate;
    }
        
    public Long getId(){
        return idReceipt;
    }
    
    public void setId(Long id){
        this.idReceipt = id;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "RentalReceipt{" + "idReceipt=" + idReceipt + ", price=" + price + ", returnDate=" + returnDate + '}';
    }
}
