/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

/**
 *
 * @author micha
 */
public enum Type {
    PLATAFORM,
    ACTION,
    FIGHT,
    FPS,
    RPG,
    ROL;
    
    public static boolean isValid(String param){
        param = param.toUpperCase();
        for (Type type : values())
            if (type.name().equals(param))   
                return true;
        return false;
    }
    
}
