/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import model.entities.Customer;

/**
 *
 * @author micha
 */
@Path("customers")
@Stateless
public class CustomerFacadeREST extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    
    public CustomerFacadeREST(){
        super(Customer.class);
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        Customer entity = super.find(id);
        
        if (entity == null) 
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No s'ha trobat cap client amb aquest identificador")
                    .build();
        
        return Response.ok().entity(entity).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findCustomers() {
        List<Customer> customers = super.findAll();
        
        if (customers == null) 
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No s'ha trobat cap client")
                    .build();
        
        
        return Response.ok().entity(customers).build();
    }
    
    @GET
    public Response verifyCustomer(@QueryParam("user") String user, @QueryParam("password") String password) {
        String jpql = "SELECT c FROM Customer c WHERE c.username = :user";
        Customer customer;
        try {
            TypedQuery<Customer> query = getEntityManager().createQuery(jpql, Customer.class);
            query.setParameter("user", user);
            customer = query.getSingleResult();
            
            if (customer == null || !customer.getPassword().equals(password)) 
                return Response.status(Response.Status.NOT_FOUND)
                    .entity("Error intentant identificar al usuari")
                    .build();
        
            return Response.status(Response.Status.OK).entity(customer.getId()).build();
            
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al servidor").build();
        }
    }
    
    @Override
    public EntityManager getEntityManager(){
        return this.em;
    }
}
