package basics;

import java.util.LinkedHashMap;

public class HashMaps {
    public static LinkedHashMap<String,Route> allRoutes;
    public static LinkedHashMap<String,Stop> allStops;
    public static LinkedHashMap<String,Trip> allTrips;
    public static LinkedHashMap<String,StopTimes> allStopTimes;
    public static LinkedHashMap<String,Trip> queriedTrips;

    public static LinkedHashMap<String, Trip> getQueriedTrips() {
        return queriedTrips;
    }

    public static void setQueriedTrips(LinkedHashMap<String, Trip> queriedTrips) {
        HashMaps.queriedTrips = queriedTrips;
    }

    public static LinkedHashMap<String, Route> getAllRoutes() {
        return allRoutes;
    }

    public static void setAllRoutes(LinkedHashMap<String, Route> allRoutes) {
        HashMaps.allRoutes = allRoutes;
    }

    public static LinkedHashMap<String, Stop> getAllStops() {
        return allStops;
    }

    public static void setAllStops(LinkedHashMap<String, Stop> allStops) {
        HashMaps.allStops = allStops;
    }

    public static LinkedHashMap<String, Trip> getAllTrips() {
        return allTrips;
    }

    public static void setAllTrips(LinkedHashMap<String, Trip> allTrips) {
        HashMaps.allTrips = allTrips;
    }

    public static LinkedHashMap<String, StopTimes> getAllStopTimes() {
        return allStopTimes;
    }

    public static void setAllStopTimes(LinkedHashMap<String, StopTimes> allStopTimes) {
        HashMaps.allStopTimes = allStopTimes;
    }
}
