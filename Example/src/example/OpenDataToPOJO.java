package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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

