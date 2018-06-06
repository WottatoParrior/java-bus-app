package storage;

import basics.Stop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Json {
    
     public static void updateStop(Stop stop) {
        ApiCaller http = new ApiCaller();
        InputStream apiResponse;
        try {
            apiResponse = http.sendGet(Double.toString(stop.getStop_lat()), Double.toString(stop.getStop_lon()));
            String jsonText=read(apiResponse);
            
           JSONParser jSONParser=new JSONParser();
           JSONObject jSONObject=(JSONObject) jSONParser.parse(jsonText);           
           JSONObject innerJsonObject=(JSONObject)jSONObject.get("address");
                      
          String county=(String)innerJsonObject.get("county");
          String suburb=(String)innerJsonObject.get("suburb");
          String road=(String)innerJsonObject.get("road");                  
          stop.EnrichApi(county, suburb, road);
           
        } catch (Exception ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static String read(InputStream rd) throws IOException {   
        BufferedReader buffRead = new BufferedReader(new InputStreamReader(rd));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = buffRead.readLine()) != null)
            result.append(line);
        
        return result.toString();
    }

}