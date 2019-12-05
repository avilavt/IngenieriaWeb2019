package entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenDataToPOJO 
{
    
    public OpenDataToPOJO(){} 
    
    public List<Lane> findAll()
    {
        return this.getListLane();
    }
    
    public int size()
    {
        return this.getListLane().size();
    }
    
     public List<String> getNames()
    {
        List<Lane> lanes = this.getListLane();
        List<String> names = new ArrayList<>();
        
        for(Lane carril: lanes)
        {
            names.add(carril.getName());
        }
        
        return names;
    }
     
    public List<Coordinates> getCoordinates()
    {
        List<Coordinates> coordinates = new ArrayList<>();
        List<Lane> lanes = this.getListLane();
        
        for(Lane carril: lanes)
        {
            Iterator<Coordinates> iter = carril.iterator();
            while(iter.hasNext())
            {
                coordinates.add(iter.next());
            }
        }
        
        return coordinates;
    }
    
    public Lane findByName(String name)
    {
        Lane carril = new Lane();
        List<Lane> lanes = this.getListLane();
        Iterator<Lane> iter = lanes.iterator();
        Boolean found = false;
        
        while(iter.hasNext() && !found)
        {
            carril = iter.next();
            found = carril.getName().equals(name);
        }
        if(!found)
        {
            carril = new Lane();
            carril.setName("Carril no existente");
            carril.setId("-1");
            carril.setOgc_fid(-1);
        }
        
        return carril;
    }
    
    public Lane findById(String id)
    {
        Lane carril = new Lane();
        List<Lane> lanes = this.getListLane();
        Iterator<Lane> iter = lanes.iterator();
        Boolean found = false;
        
        while(iter.hasNext() && !found)
        {
            carril = iter.next();
            found = carril.getId().equals(id);
        }
        if(!found)
        {
            carril = new Lane();
            carril.setName("Carril no existente");
            carril.setId("-1");
            carril.setOgc_fid(-1);
        }
        
        return carril;
    }
    
    public Lane findById(int id)
    {
        Lane carril = new Lane();
        List<Lane> lanes = this.getListLane();
        Iterator<Lane> iter = lanes.iterator();
        Boolean found = false;
        
        while(iter.hasNext() && !found)
        {
            carril = iter.next();
            found = carril.getOgc_fid() == id;
        }
        if(!found)
        {
            carril = new Lane();
            carril.setName("Carril no existente");
            carril.setId("-1");
            carril.setOgc_fid(-1);
        }
        
        return carril;
    }
    
     public Lane findByCoordinates(double latitud, double longitud)
    {
        Lane carril = new Lane();
        List<Lane> lanes = this.getListLane();
        Iterator<Lane> iter = lanes.iterator();
        Boolean found = false;
        
        while(iter.hasNext() && !found)
        {
            carril = iter.next();
            Iterator iter2 = carril.iterator();
            while(iter2.hasNext() && !found)
            {
                Coordinates coord = (Coordinates) iter2.next();
                found = coord.getLatitud() == latitud && coord.getLongitud() == longitud;
            }
        }
        if(!found)
        {
            carril = new Lane();
            carril.setName("Carril no existente");
            carril.setId("-1");
            carril.setOgc_fid(-1);
        }
        
        return carril;
    }
    
     
    private List<Lane> getListLane()
    {
       this.allTrustingTrustManager();
       String jsonString = this.getJSON("https://datosabiertos.malaga.eu/recursos/transporte/trafico/da_carrilesBici-4326.geojson");
       JSONArray array = this.stringToJSONObject(jsonString).getJSONArray("features");
       return this.JSONArrayToListLane(array);
    }   
    
    private List<Lane> JSONArrayToListLane(JSONArray array)
    {
        List<Lane> carriles = new ArrayList<>();
        String id;
        Lane carril;
        for(int index = 0; index < array.length(); index++)
        {
            JSONObject geometry = array.getJSONObject(index).getJSONObject("geometry");
            JSONObject properties = array.getJSONObject(index).getJSONObject("properties");
            id = array.getJSONObject(index).getString("id");
            carril = new Lane(id);
            
            carril.setName(properties.getString("name"));
            carril.setDescription(properties.getString("description"));
            carril.setOgc_fid(properties.getInt("ogc_fid"));
            
            JSONArray coordenadas = geometry.getJSONArray("coordinates");
            if(geometry.getString("type").equals("Point"))
            {
                carril.addCoordenadas(new Coordinates(coordenadas.getDouble(0), coordenadas.getDouble(1)));
            }
            else if(geometry.getString("type").equals("LineString"))
            {
                for(int indexCoord = 0; indexCoord < coordenadas.length(); indexCoord++)
                {
                    JSONArray coord = coordenadas.getJSONArray(indexCoord);
                    carril.addCoordenadas(new Coordinates(coord.getDouble(0), coord.getDouble(1)));
                }
            }
            carriles.add(carril);
        }
        return carriles;
    }
   
    private JSONObject stringToJSONObject(String cadena)
    {
        JSONObject jsonobj = new JSONObject();
        try 
        {
            jsonobj = new JSONObject(cadena);
        }
        catch (JSONException err)
        {
            System.err.println("Error "+ err.toString());
        }
        return jsonobj; 
    }

    private String getJSON(String url) 
    {
        HttpURLConnection c = null;
        try 
        {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            int status = c.getResponseCode();

            switch (status) 
            {
                case 200:
                case 201:
                        BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) 
                        {
                            sb.append(line+"\n");
                        }
                        br.close();
                        return sb.toString();
            }
        }
        catch (MalformedURLException ex) 
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } 
        finally 
        {
            if (c != null) 
            {
                try 
                {
                    c.disconnect();
                }
                catch (Exception ex)
                {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    private void allTrustingTrustManager()
    {
        // Create a trust manager that does not validate certificate chains like the default
        TrustManager[] trustAllCerts = new TrustManager[]
        {
            new X509TrustManager() 
            {   

                public java.security.cert.X509Certificate[] getAcceptedIssuers()
                {
                    return null;
                }

                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                {
                    //No need to implement.
                }

                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                {
                    //No need to implement.
                }
            }
        };

        // Install the all-trusting trust manager
        try 
        {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }   
}    

