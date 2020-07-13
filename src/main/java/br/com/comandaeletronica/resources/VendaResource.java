/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comandaeletronica.resources;

import br.com.comandaeletronica.models.Venda;
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
@Path("vendas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VendaResource {
    
    @PersistenceContext(unitName = "ComandaPU")
    EntityManager entityManager;
    
    @GET
    public List<Venda> getVendas() {
        return entityManager
                .createNamedQuery("Venda.findAll", Venda.class)
                .getResultList();
    }
    
    @POST
    public Response addVenda(Venda venda) {
        entityManager.persist(venda);
        return Response
                .status(Response.Status.CREATED)
                .entity(venda)
                .build();
    }
    
    @GET
    @Path("{id}")
    public Venda getVenda(@PathParam("id") UUID id) {
        return entityManager.find(Venda.class, id);
    }
        
    @DELETE
    @Path("{id}")
    public void removeVenda(@PathParam("id") UUID id) {
        Venda venda = entityManager.find(Venda.class, id);
        entityManager.remove(venda);
    }
    
    @PUT
    @Path("{id}")
    public Venda updateVenda(@PathParam("id") UUID id, Venda a) {
        a.setId(id);
        entityManager.merge(a);
        return a;
    }
    
}
