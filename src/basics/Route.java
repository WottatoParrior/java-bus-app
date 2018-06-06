package basics;

import java.util.LinkedHashMap;

public class Route{
    private String route_id;
    private String route_short_name;
    private String route_long_name;
    private int route_type;
    private String route_color;
    private String route_text_color;
    private LinkedHashMap<String, Trip> trips = new LinkedHashMap<String, Trip>();

    public Route() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public LinkedHashMap<String, Trip> getTrips() {
        return trips;
    }

    public void setTrips(LinkedHashMap<String, Trip> trips) {
        this.trips = trips;
    }
 
    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getRoute_short_name() {
        return route_short_name;
    }

    public void setRoute_short_name(String route_short_name) {
        this.route_short_name = route_short_name;
    }

    public String getRoute_long_name() {
        return route_long_name;
    }

    public void setRoute_long_name(String route_long_name) {
        this.route_long_name = route_long_name;
    }

    public int getRoute_type() {
        return route_type;
    }

    public void setRoute_type(int route_type) {
        this.route_type = route_type;
    }

    public String getRoute_color() {
        return route_color;
    }

    public void setRoute_color(String route_color) {
        this.route_color = route_color;
    }

    public String getRoute_text_color() {
        return route_text_color;
    }

    public void setRoute_text_color(String route_text_color) {
        this.route_text_color = route_text_color;
    }

    public Route(String route_id, String route_short_name, String route_long_name, /*String route_desc,*/ int route_type, String route_color, String route_text_color) {
        this.route_id = route_id;
        this.route_short_name = route_short_name;
        this.route_long_name = route_long_name;
        this.route_type = route_type;
        this.route_color = route_color;
        this.route_text_color = route_text_color;
    }
    
    public Route(String routerow) {
        String[] tokens= routerow.split(",");
        this.route_id = tokens[0].substring(0,tokens[0].length());
        this.route_short_name = tokens[1].substring(0,tokens[1].length());
        this.route_long_name = tokens[2].substring(0,tokens[2].length());
        this.route_type = Integer.parseInt(tokens[4]);
        this.route_color = tokens[5].substring(1,tokens[5].length()-1);
        this.route_text_color = tokens[6].substring(0,tokens[6].length());
    }
    
     @Override
    public String toString() {
        return route_id + "," +route_short_name + "," + route_long_name + "," + route_type + ","  + route_color + "," + route_text_color;
    }
}
