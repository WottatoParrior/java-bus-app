package storage;

import static basics.HashMaps.allStopTimes;
import static basics.HashMaps.setAllStopTimes;
import basics.StopTimes;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
//Comments can be found in RouteFileManager

public class StopTimesFileManager {
    public StopTimes readStopTimesInfoFromRow (String row) {
        StopTimes a= new StopTimes(row);
        return a;
    }
    
    public LinkedHashMap<String,StopTimes> readStopTimesInfoFromFileHash(String filename) {
        LinkedHashMap<String,StopTimes> all =new LinkedHashMap<String,StopTimes>();
        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF8"));
            String row = br.readLine();
            row = br.readLine();
            while(row!=null) {
                StopTimes c = readStopTimesInfoFromRow(row);
                all.put(c.getStoptime_id(),c);
                row=br.readLine();
            }
            br.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RouteFileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | StringIndexOutOfBoundsException ex) {
            Logger.getLogger(RouteFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setAllStopTimes(all);
        
        return allStopTimes;
        
    }
}
