package storage;

import static basics.HashMaps.allStops;
import static basics.HashMaps.setAllStops;
import basics.Stop;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
//Comments can be found in RouteFileManager

public class StopFileManager {

    public Stop readStopInfoFromRow(String row) {
        Stop a = new Stop(row);
        return a;
    }
    
    public LinkedHashMap<String, Stop> readStopInfoFromFile(String filename) {
        LinkedHashMap<String, Stop> all = new LinkedHashMap<String, Stop>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String row = br.readLine(); 
            row = br.readLine(); 
            while (row != null) { 
                Stop c = readStopInfoFromRow(row);
                all.put(c.getStop_id(), c);
                row = br.readLine(); 
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StopFileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | StringIndexOutOfBoundsException ex) {
            Logger.getLogger(StopFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        setAllStops(all);
        return allStops;
    }
}
