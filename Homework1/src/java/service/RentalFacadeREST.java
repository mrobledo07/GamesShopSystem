/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.entities.Customer;
import model.entities.Game;
import model.entities.Rental;
import model.entities.RentalReceipt;
import model.entities.Shop;

/**
 *
 * @author micha
 */
@Path("rentals")
@Stateless
public class RentalFacadeREST extends AbstractFacade<Rental> {
    
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    
    public RentalFacadeREST(){
        super(Rental.class);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserRentals(@QueryParam("id") Long id){
        String jpql = "SELECT r FROM Rental r WHERE r.customer.id = :id";
        List<String> llista;
        List<Rental> llistaReceipts; 
        
        try {
            TypedQuery<Rental> query = getEntityManager().createQuery(jpql, Rental.class);
            query.setParameter("id", id);
            llistaReceipts = query.getResultList();
            llista = llistaReceipts.stream().map(rental -> rental.toString()).collect(Collectors.toList());
            
            return Response.status(Response.Status.OK).entity(llista).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al servidor").build();
        }
    }
    
    @POST
    @Secured
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createRental(Rental rental){
        try {
            Rental rentalBd = new Rental();
            rentalBd.setGames(new LinkedList<>());
            
            List<Game> unavailable = new LinkedList<>();
            RentalReceipt receipt = processRental(rental, rentalBd, unavailable, getEntityManager());
            if (receipt == null)
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Un dels jocs o el client són incorrectes")
                        .build();
            
            if (unavailable.size() == rental.getGames().size())
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Cap dels jocs està disponible, no s'ha creat la comanda")
                        .build();
            
            String strUnavailable;
            if (unavailable.isEmpty()){
                strUnavailable = "Tots els jocs estaven disponibles i s'han afegit a la comanda";
            }else{
                strUnavailable = "Aquests jocs no estan disponibles i no s'han afegit a la comanda: ";
                Game g = unavailable.get(0);
                unavailable.remove(0);
                strUnavailable += g.getId();
                for (Game g1 : unavailable)
                    strUnavailable += ", " + g1.getId();  
            }
            
            super.create(rentalBd);
            // Obtenim el id
            receipt.setId(rentalBd.getId());
            
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("message", strUnavailable);
            responseMap.put("receipt", receipt);
            
            return Response.ok()
                    .entity(responseMap)
                    .build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear l'alquiler")
                    .build();
        }
    }
    
    public static RentalReceipt processRental(Rental rent, Rental rentBd, List<Game> unavailable, EntityManager em){
        
        
        double price = 0;
        
        // Obtenim els jocs
        List<Game> rentalGames = rent.getGames();
        
        
        // Per cadascun dels jocs de la cistella
        for (Game game : rentalGames){  
            Game gameBd = em.find(Game.class, game.getId());
            if (gameBd == null) return null;
            else {
                
                if (!gameBd.getAvailability())
                    unavailable.add(gameBd);
                else{
                    price += gameBd.getPrice();
                    // Obtener lista de tiendas asociadas con el juego
                    List<Shop> shops = gameBd.getShops();
                    
                    // Escollim l'última botiga de la llista i la desvinculem del joc
                    Shop selectedShop = shops.get(shops.size() - 1);
                    shops.remove(shops.size() - 1);
                    selectedShop.getGames().remove(gameBd);
                    
                    if (shops.isEmpty()) gameBd.setAvailability(false);
                    
                    rentBd.getGames().add(gameBd);
                    // Persistim els canvis
                    em.merge(selectedShop);
                    em.merge(gameBd);
                }
            }

        }
        
        // Obtenim data actual
        Date rentalDate = new Date();
        // Obtenim instància de Calendar amb data actual
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rentalDate);
        
        // Li sumem 1 setmana (suposem que els alquilers duren 1 setmana)
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        
        // Obtenim la data de retorn
        Date returnDate = calendar.getTime();
        
        RentalReceipt receipt = new RentalReceipt(price, returnDate);
        rentBd.setReceipt(receipt);
        
        // Obtenim el customer
        Customer customer = em.find(Customer.class, rent.getCustomer().getId());
        if (customer == null)
            return null;
        else {
            rentBd.setCustomer(customer);
        }
        
        return receipt;
    }
    
    
   @Override
    public EntityManager getEntityManager(){
        return this.em;
    }
    
}
