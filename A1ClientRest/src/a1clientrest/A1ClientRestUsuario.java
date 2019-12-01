package a1clientrest;

import client.UsuarioClient;
import entity.Usuario;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class A1ClientRestUsuario {

    public static void main(String[] args) 
    {
        A1ClientRestUsuario a1 = new A1ClientRestUsuario();
        System.out.println("********************************************************");
        a1.read();
        int lastId = a1.lastId();
        System.out.println("********************************************************");
        System.out.println("LastId: " + lastId);
        a1.create("Laffey", "sleepy_bunny@eagle.union", "Destroyer");
        //a1.update("4", "Usuario de Prueba");
        Usuario us = a1.findById("4");
                System.out.println("Id user: " + us.getIdUsuario());
                System.out.println("Name: " + us.getNombre());
                System.out.println("Email: " + us.getEmail());
                System.out.println("Role: " + us.getRol());
        System.out.println("********************************************************");
        List<Usuario> users = a1.findByRole("Administrador");
        System.out.println("Usuarios con rol administrador");
        if(users != null)
        {
            for(Usuario u: users)
            {
                System.out.println("Id user: " + u.getIdUsuario());
                System.out.println("Name: " + u.getNombre());
                System.out.println("Email: " + u.getEmail());
                System.out.println("Role: " + u.getRol());
            }
        }
        else
        {
            System.out.println("No existen usuarios con ese rol");
        }
        System.out.println("********************************************************");
        List<Usuario> users2 = a1.findByRole("Usuario");
        System.out.println("Usuarios con rol usuario");
        if(users != null)
        {
            for(Usuario u: users2)
            {
                System.out.println("Id user: " + u.getIdUsuario());
                System.out.println("Name: " + u.getNombre());
                System.out.println("Email: " + u.getEmail());
                System.out.println("Role: " + u.getRol());
            }
        }
        else
        {
            System.out.println("No existen usuarios con ese rol");
        }
        System.out.println("********************************************************");
        List<Usuario> users3 = a1.findByName("la");
        System.out.println("Usuarios con nombre que contenga \"la\"");
        if(users != null)
        {
            for(Usuario u: users3)
            {
                System.out.println("Id user: " + u.getIdUsuario());
                System.out.println("Name: " + u.getNombre());
                System.out.println("Email: " + u.getEmail());
                System.out.println("Role: " + u.getRol());
            }
        }
        else
        {
            System.out.println("No existen usuarios con ese nombre");
        }
    }
    
    public int read()
    {
        int lastId = 0;
        UsuarioClient user = new UsuarioClient();
        Response resp = user.findAll_XML(Response.class);
        if(resp.getStatus() == 200)
        {
            GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>(){};
            List<Usuario> users = resp.readEntity(genericType);
            for(Usuario us: users)
            {
                System.out.println("Id user: " + us.getIdUsuario());
                System.out.println("Name: " + us.getNombre());
                System.out.println("Email: " + us.getEmail());
                System.out.println("Role: " + us.getRol());
                lastId = us.getIdUsuario();
            }
        }
        return lastId;
    }
    
    public Usuario findById(String idUsuario)
    {
        UsuarioClient user = new UsuarioClient();
        Response resp = user.find_XML(Response.class, idUsuario);
        GenericType<Usuario> genericType = new GenericType<Usuario>(){};
        return resp.readEntity(genericType);
    }
    
    public List<Usuario> findByRole(String role)
    {
        UsuarioClient user = new UsuarioClient();
        Response resp = user.findByRol_XML(Response.class, role);
        if(resp.getStatus() == 200)
        {
            GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>(){};
            return resp.readEntity(genericType);
        }
        return null;
    }
    
    public List<Usuario> findByName(String patron)
    {
        UsuarioClient user = new UsuarioClient();
        Response resp = user.findByPatronNombre_XML(Response.class, patron);
        if(resp.getStatus() == 200)
        {
            GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>(){};
            return resp.readEntity(genericType);
        }
        return null;
    }
    
    public int lastId()
    {
        int lastId = 0;
        UsuarioClient user = new UsuarioClient();
        Response resp = user.findAll_XML(Response.class);
        if(resp.getStatus() == 200)
        {
            GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>(){};
            List<Usuario> users = resp.readEntity(genericType);
            lastId = users.get(users.size()-1).getIdUsuario();
        }
        return lastId;
    }
    
    public void create(String newName, String newEmail, String newRole)
    {
        UsuarioClient user = new UsuarioClient();
        Usuario usuario = new Usuario();
        usuario.setNombre(newName);
        usuario.setEmail(newEmail);
        usuario.setRol(newRole);
        try
        {
            user.create_XML(usuario);
        }
        catch(ClientErrorException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    
    public void update(String idUsuario, String newName)
    {
       UsuarioClient user = new UsuarioClient();
       Response resp = user.find_XML(Response.class, idUsuario);
       GenericType<Usuario> genericType = new GenericType<Usuario>(){};
       Usuario usuario = resp.readEntity(genericType);
       usuario.setNombre(newName);
       user.edit_XML(usuario, idUsuario);
    }
    
    public void delete(String idUsuario)
    {
        UsuarioClient user = new UsuarioClient();
        user.remove(idUsuario);
    }
}
