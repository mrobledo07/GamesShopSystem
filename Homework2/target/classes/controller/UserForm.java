package deim.urv.cat.homework2.controller;

import jakarta.inject.Named;
import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.FormParam;
import java.io.Serializable;
import jakarta.enterprise.context.RequestScoped;


@Named("userForm")
@RequestScoped
public class UserForm implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    
    // JSR 303 validation
    @NotBlank
    @FormParam("username")
    @MvcBinding
    private String username;

    @NotBlank
    @FormParam("password")
    @MvcBinding
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }   
}
