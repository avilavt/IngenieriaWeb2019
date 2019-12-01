/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.service;

import entity.Comentario;
import java.sql.Date;
import java.util.List;
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

/**
 *
 * @author avila
 */
@Stateless
@Path("entity.comentario")
public class ComentarioFacadeREST extends AbstractFacade<Comentario> {

    @PersistenceContext(unitName = "A1ServerRestTestPU")
    private EntityManager em;

    public ComentarioFacadeREST() {
        super(Comentario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Comentario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Comentario entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Comentario find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    // Consulta que devuelve los comentarios realizados por el usuario "id"
    @GET
    @Path("usuario/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Comentario> findByUsuario(@PathParam("id") Integer id) {
        return em.createNamedQuery("Comentario.custom.findByUsuarioId").setParameter("usuarioId", id).getResultList();
    }
    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Comentario> findAll() {
        return super.findAll();
    }

    //Consultar los comentarios que en su contenido se halla un patrón
    @GET
    @Path("contenido/{contenido}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Comentario> findByPatronNombre(@PathParam("contenido") String contenido)
    {
        String patron = "%" + contenido + "%";
        return em.createNamedQuery("Usuario.custom.findByPatron").setParameter("contenido", patron).getResultList();
    }
    
    //Consulta que devuelve los comentarios de una fecha determinada
    @GET
    @Path("fecha/{year}/{month}/{day}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Comentario> findByDate(@PathParam("year") Integer year, @PathParam("month") Integer month
                            ,@PathParam("day") Integer day) 
    {
        String fechaStr = year + "-" + month + "-" + day;
        Date fechaCreacion = Date.valueOf(fechaStr);
        return em.createNamedQuery("Comentario.custom.findByDate").setParameter("fechaCreacion"
                , fechaCreacion).getResultList();
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Comentario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
