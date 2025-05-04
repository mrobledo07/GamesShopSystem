/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.ejb.Stateless;
import model.entities.Game;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.entities.Console;
import model.entities.Type;
/**
 *
 * @author micha
 */
@Path("games")
@Stateless
public class GameFacadeREST extends AbstractFacade<Game> {
    
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    
    public GameFacadeREST(){
        super(Game.class);
    }
       
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getGames(@QueryParam("type") String type, @QueryParam("console") String console) {
        try {
            
            List<String> invalidTypes = new LinkedList<>();
            if (type != null && !Type.isValid(type)){
                invalidTypes.add("type");
            }
            
            if (console != null && !Console.isValid(console)){
                invalidTypes.add("console");
            }
            
            if (!invalidTypes.isEmpty()){
                String strInvalidTypes = String.join(", ", invalidTypes);
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Parametres incorrectes: " + strInvalidTypes)
                        .build();
            }

            // Consulta a la base de dades amb els filtres
            List<Game> games = findGames(type, console);

            // Retorna la llista de jocs en format JSON
            return Response.ok().entity(games).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al servidor").build();
        }
    }

    
    private List<Game> findGames(String type, String console){
        String jpql = "SELECT g FROM Game g WHERE 1=1";
        Map<String, Object> parameters = new HashMap<>();

        if (type != null) {
            jpql += " AND g.type = :type";
            parameters.put("type", Type.valueOf(type.toUpperCase()));
        }
        if (console != null) {
            jpql += " AND g.gameConsole = :console";
            parameters.put("console", Console.valueOf(console.toUpperCase()));
        }
        
        jpql += " ORDER BY g.name ASC";

        TypedQuery<Game> query = getEntityManager().createQuery(jpql, Game.class);
        parameters.forEach((key, value) -> query.setParameter(key, value));

        return query.getResultList();
    }

    
    
    @Override
    public EntityManager getEntityManager(){
        return this.em;
    }
}
