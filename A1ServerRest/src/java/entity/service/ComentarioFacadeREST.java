/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.service;

import entity.Comentario;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @PersistenceContext(unitName = "A1ServerRestPU")
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

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Comentario> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Comentario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    //Comentario.findByContenidoParcial
    @GET
    @Path("contenido/{contenido}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Comentario> findByContenidoParcial(@PathParam("contenido") String contenido)
    {
        return em.createNamedQuery("Comentario.findByContenidoParcial").setParameter("contenido", contenido).getResultList();
    }
    
    @GET
    @Path("fecha/{fecha}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Comentario> findByDate(@PathParam("fecha") String string)
    {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            date = (Date) format.parse(string);
        } catch (ParseException ex) {
            Logger.getLogger(ComentarioFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em.createNamedQuery("Comentario.findByFechaCreacion").setParameter("fechaCreacion", date).getResultList();
    }
    
    @GET
    @Path("{day}/{month}/{year}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Comentario> findByDate(@PathParam("day") Integer day, @PathParam("month") Integer month, @PathParam("year") Integer year)
    {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        String str = year + "-" + month + "-" + day; 
        try {
            date = (Date) format.parse(str);
        } catch (ParseException ex) {
            Logger.getLogger(ComentarioFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em.createNamedQuery("Comentario.findByFechaCreacion").setParameter("fechaCreacion", date).getResultList();
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
