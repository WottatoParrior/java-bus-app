package basics;

import java.util.LinkedHashMap;

public class Trip {
    private String route_id;
    private String service_id;
    private String trip_id;
    private String trip_headsign;
    private int direction_id;
    private int trip_repeats;
    
    public LinkedHashMap<String, Stop> stops;

    public Trip() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        public void addStop(Stop s) {
        LinkedHashMap<String, Stop> currStops = this.getStops();
        currStops.put(s.getStop_id(), s);
        setStops(currStops);
    }

    public LinkedHashMap<String, Stop> getStops() {
        return stops;
    }

    public void setStops(LinkedHashMap<String, Stop> stopsHash) {
        this.stops = stops;
    }
    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getTrip_headsign() {
        return trip_headsign;
    }

    public void setTrip_headsign(String trip_headsign) {
        this.trip_headsign = trip_headsign;
    }

    public int getDirection_id() {
        return direction_id;
    }

    public void setDirection_id(int direction_id) {
        this.direction_id = direction_id;
    }

    public int getTrip_repeats() {
        return trip_repeats;
    }

    public void setTrip_repeats(int trip_repeats) {
        this.trip_repeats = trip_repeats;
    }

    public Trip(String route_id, String service_id, String trip_id, String trip_headsign, int direction_id, int trip_repeats) {
        this.route_id = route_id;
        this.service_id = service_id;
        this.trip_id = trip_id;
        this.trip_headsign = trip_headsign;
        this.direction_id = direction_id;
        this.trip_repeats = trip_repeats;
    }
    
    public Trip(String triprow) {
        String[] tokens= triprow.split(",");
        this.route_id = tokens[0].substring(0,tokens[0].length());
        this.service_id = tokens[1].substring(0,tokens[1].length());
        this.trip_id = tokens[2].substring(0,tokens[2].length());
        this.trip_headsign = tokens[3].substring(0,tokens[3].length());
        this.direction_id = Integer.parseInt(tokens[4]);
        if(tokens.length==6) { //Checking if there is a null value or not
            this.trip_repeats = Integer.parseInt(tokens[5]);
        }else{
            this.trip_repeats =-1;
        }
    }
    
    @Override
    public String toString() {
        return route_id + "," +service_id + "," + trip_id + "," + trip_headsign + ","  + direction_id + "," + trip_repeats;
    }
}
