/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;


/**
 *
 * @author micha
 */
public enum Console{
    GAME_BOY,
    MEGA_DRIVE,
    ATARI_2600,
    NINTENDO_64;
    
    public static boolean isValid(String param){
        param = param.toUpperCase();
        for (Console type : values())
            if (type.name().equals(param))   
                return true;
        return false;
    }
}

