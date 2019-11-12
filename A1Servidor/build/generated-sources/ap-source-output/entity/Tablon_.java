package entity;

import entity.Comentario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-12T20:13:34")
@StaticMetamodel(Tablon.class)
public class Tablon_ { 

    public static volatile SingularAttribute<Tablon, String> informacion;
    public static volatile SingularAttribute<Tablon, Integer> idTablon;
    public static volatile SingularAttribute<Tablon, Date> fechaCreacion;
    public static volatile CollectionAttribute<Tablon, Comentario> comentarioCollection;

}