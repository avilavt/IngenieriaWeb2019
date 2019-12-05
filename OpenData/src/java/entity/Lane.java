package entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Lane
{
    private String id;
    private String name;
    private String description;
    private int ogc_fid;
    private List<Coordinates> coordenadas;

    public Lane(String id, String name, String description, int ogc_fid, List<Coordinates> coordenadas) 
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ogc_fid = ogc_fid;
        this.coordenadas = coordenadas;
    }

    public Lane() {}

    public Lane(String id) 
    {
        this.id = id;
        this.coordenadas = new ArrayList<>();
        this.description = "";
        this.name = "";
        this.ogc_fid = 0;
    }

    public String getId() 
    {
        return id;
    }

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public int getOgc_fid() 
    {
        return ogc_fid;
    }

    public void setOgc_fid(int ogc_fid) 
    {
        this.ogc_fid = ogc_fid;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lane other = (Lane) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() 
    {
        return "Lane{" + "id=" + id + 
                ", \n name=" + name + 
                ", \n coordenadas=" + coordenadas + 
                ", \n description=" + description + 
                ", \n ogc_fid=" + ogc_fid + '}';
    }

    public Iterator iterator()
    {
        return this.coordenadas.iterator();
    }

    public void setCoordenadas(List<Coordinates> coordenadas) 
    {
        this.coordenadas = coordenadas;
    }    
    
     public void addCoordenadas(Coordinates nuevo)
     {
         this.coordenadas.add(nuevo);
     }
}
