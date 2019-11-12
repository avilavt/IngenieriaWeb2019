package entity;

import entity.Tablon;
import entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-12T20:26:54")
@StaticMetamodel(Comentario.class)
public class Comentario_ { 

    public static volatile SingularAttribute<Comentario, String> contenido;
    public static volatile SingularAttribute<Comentario, Tablon> idTablon;
    public static volatile CollectionAttribute<Comentario, Usuario> usuarioCollection;
    public static volatile SingularAttribute<Comentario, Integer> idComentario;

}