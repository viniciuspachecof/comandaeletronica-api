/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comandaeletronica.resources;

import br.com.comandaeletronica.models.Produto;
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
@Path("produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoResource {
    
    @PersistenceContext(unitName = "ComandaPU")
    EntityManager entityManager;
    
    @GET
    public List<Produto> getProdutos() {
        return entityManager
                .createQuery("SELECT a FROM Produto a", Produto.class)
                .getResultList();
    }
    
    @POST
    public Response addProduto(Produto produto) {
        entityManager.persist(produto);
        return Response
                .status(Response.Status.CREATED)
                .entity(produto)
                .build();
    }
    
    @GET
    @Path("{id}")
    public Produto getProduto(@PathParam("id") UUID id) {
        return entityManager.find(Produto.class, id);
    }
        
    @DELETE
    @Path("{id}")
    public void removeProduto(@PathParam("id") UUID id) {
        Produto produto = entityManager.find(Produto.class, id);
        entityManager.remove(produto);
    }
    
    @PUT
    @Path("{id}")
    public Produto updateProduto(@PathParam("id") UUID id, Produto a) {
        a.setId(id);
        entityManager.merge(a);
        return a;
    }   
    
}
