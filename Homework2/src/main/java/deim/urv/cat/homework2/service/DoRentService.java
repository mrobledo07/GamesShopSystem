/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.Rental;
import java.util.Map;

/**
 *
 * @author micha
 */
public interface DoRentService {
    public Map doRent(Rental rental, String credentials);
}
