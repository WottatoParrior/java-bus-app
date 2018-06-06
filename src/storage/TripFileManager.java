package storage;

import static basics.HashMaps.allTrips;
import static basics.HashMaps.setAllTrips;
import basics.Trip;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TripFileManager {

    public Trip readTripInfoFromRow(String row) {
        Trip a = new Trip(row);
        return a;
    }

    public LinkedHashMap<String, Trip> readTripInfoFromFile(String filename) {

        LinkedHashMap<String, Trip> all = new LinkedHashMap<String, Trip>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF8"));
            String row = br.readLine();
            row = br.readLine();
            while (row != null) {
                
                Trip c = readTripInfoFromRow(row);
                all.put(c.getTrip_id(), c);
                row = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TripFileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | StringIndexOutOfBoundsException ex) {
            Logger.getLogger(TripFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        setAllTrips(all);

        return allTrips;
    }
}
