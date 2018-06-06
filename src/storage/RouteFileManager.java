package storage;

import static basics.HashMaps.allRoutes;
import static basics.HashMaps.setAllRoutes;
import basics.Route;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RouteFileManager {

    public Route readRouteInfoFromRow(String row) {
        Route a = new Route(row);
        return a;
    }

    public LinkedHashMap<String, Route> readRouteInfoFromFile(String filename) {
        LinkedHashMap<String, Route> all = new LinkedHashMap<String, Route>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String row = br.readLine(); 
            row = br.readLine(); 
            while (row != null) {
                Route c = readRouteInfoFromRow(row);
                all.put(c.getRoute_id(), c);
                row = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RouteFileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | StringIndexOutOfBoundsException ex) {
            Logger.getLogger(RouteFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        setAllRoutes(all);
        return allRoutes;
    }

}
