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

/**
 *
 * @author avila
 */
public class Example 
{

    public static void main(String[] args) 
    {
        OpenDataToPOJO op = new OpenDataToPOJO();
        System.out.println("Number of lanes: "+op.size());
        for(Lane lane: op.findAll())
        {
            System.out.println(lane.toString());
        }
    }
}