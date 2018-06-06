
package storage;

import static basics.HashMaps.allRoutes;
import static basics.HashMaps.allStopTimes;
import static basics.HashMaps.allStops;
import static basics.HashMaps.allTrips;
import basics.Route;
import basics.Stop;
import basics.Trip;
import java.util.LinkedHashMap;
import java.util.Set;

public class MainHelper {
    public static int count =0;
    public static void HashmapFiller(){
        

        
        String routes_path = "geodata/routes.txt";
        RouteFileManager rfm = new RouteFileManager();
        rfm.readRouteInfoFromFile(routes_path);
        System.out.println("Done with routes!");

        
        String trips_path = "geodata/trips.txt";
        TripFileManager tfm = new TripFileManager();
        tfm.readTripInfoFromFile(trips_path);
        System.out.println("Done with trips!");

      
        String stops_path = "geodata/stops.txt";
        StopFileManager sfm = new StopFileManager();
        sfm.readStopInfoFromFile(stops_path);
        System.out.println("Done with stops!");

       
        String stop_times_path = "geodata/stop_times.txt";
        StopTimesFileManager stfm = new StopTimesFileManager();
        stfm.readStopTimesInfoFromFileHash(stop_times_path);
        System.out.println("Done with Stop Times!");
        for(String key: allStopTimes.keySet()) {
            System.out.println(allStopTimes.get(key));
        }
        System.out.println(allTrips.size());
        System.out.println(allStops.size());
        System.out.println(allRoutes.size());
        System.out.println(allStopTimes.size());
     
        
        

       LinkedHashMap<String, String> test = new LinkedHashMap<String, String>();;      
            for (String key : allRoutes.keySet()) { 
                Route currentRoute = allRoutes.get(key);
                String eachRouteId = currentRoute.getRoute_id();
                
                test.put(eachRouteId,"");
            }
            System.out.println(test);
            

        System.out.println("Done!");

}

    public static void JsonStopUpdate(){
        int itemsShown = 0;
        int maxItemsShown = 10;
        
        for (String key : allStops.keySet()) {
            itemsShown++;
            Stop currStop = allStops.get(key);
            storage.Json.updateStop(currStop);
        
            if (itemsShown >= maxItemsShown) {
                break;
            }
        }

    }
}
