/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.service.ReceiptsService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import java.util.List;

/**
 *
 * @author micha
 */
@Controller
@Path("Receipts")
public class ReceiptsController {
    
    @Inject ReceiptsService service;
     
    @Context
    private HttpServletRequest request;
    
    @GET
    public String showReceipts(){
        HttpSession session = request.getSession();
        UserForm user = (UserForm) session.getAttribute("user");
        List<String> receipts = service.getReceipts(user.getId());
        
        request.setAttribute("receipts", receipts);
        
        return "receipts-list.jsp";
    }
}
