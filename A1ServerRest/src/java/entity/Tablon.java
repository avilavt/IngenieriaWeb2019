/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author avila
 */
@Entity
@Table(name = "TABLON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablon.findAll", query = "SELECT t FROM Tablon t")
    , @NamedQuery(name = "Tablon.findByIdTablon", query = "SELECT t FROM Tablon t WHERE t.idTablon = :idTablon")
    , @NamedQuery(name = "Tablon.findByFechaCreacion", query = "SELECT t FROM Tablon t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Tablon.findByInformacion", query = "SELECT t FROM Tablon t WHERE t.informacion = :informacion")})
public class Tablon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TABLON")
    private Integer idTablon;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Size(max = 45)
    @Column(name = "INFORMACION")
    private String informacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTablon")
    private Collection<Comentario> comentarioCollection;

    public Tablon() {
    }

    public Tablon(Integer idTablon) {
        this.idTablon = idTablon;
    }

    public Integer getIdTablon() {
        return idTablon;
    }

    public void setIdTablon(Integer idTablon) {
        this.idTablon = idTablon;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    @XmlTransient
    public Collection<Comentario> getComentarioCollection() {
        return comentarioCollection;
    }

    public void setComentarioCollection(Collection<Comentario> comentarioCollection) {
        this.comentarioCollection = comentarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTablon != null ? idTablon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablon)) {
            return false;
        }
        Tablon other = (Tablon) object;
        if ((this.idTablon == null && other.idTablon != null) || (this.idTablon != null && !this.idTablon.equals(other.idTablon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tablon[ idTablon=" + idTablon + " ]";
    }
    
}
