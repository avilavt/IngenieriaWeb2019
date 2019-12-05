/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author avila
 */
@WebService(serviceName = "BiciLane")
@Stateless()
public class BiciLane {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "size")
    public int size() 
    {
        return (new OpenDataToPOJO()).size();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findAll")
    public List<Lane> findAll() 
    {
        return (new OpenDataToPOJO()).findAll();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getNames")
    public List<String> getNames() 
    {
        return (new OpenDataToPOJO()).getNames();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCoordinates")
    public List<Coordinates> getCoordinates() 
    {
        return (new OpenDataToPOJO()).getCoordinates();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findByName")
    public Lane findByName(@WebParam(name = "name") String name) 
    {
        return (new OpenDataToPOJO()).findByName(name);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findById")
    public Lane findById(@WebParam(name = "id") String id) 
    {
        return (new OpenDataToPOJO()).findById(id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findByOgcFId")
    public Lane findByOgcFId(@WebParam(name = "id") int id) 
    {
        return (new OpenDataToPOJO()).findById(id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findByCoordinates")
    public Lane findByCoordinates(@WebParam(name = "latitud") double latitud, @WebParam(name = "longitud") double longitud) 
    {
        return (new OpenDataToPOJO()).findByCoordinates(latitud, longitud);
    }


}
