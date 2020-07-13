/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comandaeletronica.resources;

import br.com.comandaeletronica.models.Comanda;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author vinic
 */
@Stateless
@Path("comandas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComandaResource {
    
    @PersistenceContext(unitName = "ComandaPU")
    EntityManager entityManager;
    
    @GET
    public List<Comanda> getComandas() {
        return entityManager
                .createNamedQuery("Comanda.findAll", Comanda.class)
                .getResultList();
    }
    
    @POST
    public Response addComanda(Comanda comanda) {
        entityManager.persist(comanda);
        return Response
                .status(Response.Status.CREATED)
                .entity(comanda)
                .build();
    }
    
    @GET
    @Path("{id}")
    public Comanda getComanda(@PathParam("id") UUID id) {
        return entityManager.find(Comanda.class, id);
    }
        
    @DELETE
    @Path("{id}")
    public void removeComanda(@PathParam("id") UUID id) {
        Comanda comanda = entityManager.find(Comanda.class, id);
        entityManager.remove(comanda);
    }
    
    @PUT
    @Path("{id}")
    public Comanda updateComanda(@PathParam("id") UUID id, Comanda a) {
        a.setId(id);
        entityManager.merge(a);
        return a;
    }  
}
