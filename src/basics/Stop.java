package basics;

public class Stop {
    private String stop_id;
    private int stop_code;
    private String stop_name;
    private String stop_desc;
    private double stop_lat;
    private double stop_lon;
    private int location_type;
    
    private String road;
    private String suburb;
    private String county;
    
    public void EnrichApi(String road,String suburb,String county){
        
        this.road=road;
        this.suburb=suburb;
        this.county=county;
    }

    public String getStop_id() {
        return stop_id;
    }

    public void setStop_id(String stop_id) {
        this.stop_id = stop_id;
    }

    public int getStop_code() {
        return stop_code;
    }

    public void setStop_code(int stop_code) {
        this.stop_code = stop_code;
    }

    public String getStop_name() {
        return stop_name;
    }

    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }

    public String getStop_desc() {
        return stop_desc;
    }

    public void setStop_desc(String stop_desc) {
        this.stop_desc = stop_desc;
    }

    public double getStop_lat() {
        return stop_lat;
    }

    public void setStop_lat(double stop_lat) {
        this.stop_lat = stop_lat;
    }

    public double getStop_lon() {
        return stop_lon;
    }

    public void setStop_lon(double stop_lon) {
        this.stop_lon = stop_lon;
    }

    public int getLocation_type() {
        return location_type;
    }

    public void setLocation_type(int location_type) {
        this.location_type = location_type;
    }

    //For API usage
    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
    
    
    public Stop(String stop_id, int stop_code, String stop_name, String stop_desc, double stop_lat, double stop_lon, int location_type) {
        this.stop_id = stop_id;
        this.stop_code = stop_code;
        this.stop_name = stop_name;
        this.stop_desc = stop_desc;
        this.stop_lat = stop_lat;
        this.stop_lon = stop_lon;
        this.location_type = location_type;
    }
    
     public Stop(String stoprow) { 
        String[] tokens= stoprow.split(",");
        this.stop_id = tokens[0].substring(0,tokens[0].length());
        this.stop_code = Integer.parseInt(tokens[1]);
        this.stop_name = tokens[2].substring(0,tokens[2].length());
        this.stop_desc = tokens[3].substring(0,tokens[3].length());
        this.stop_lat = Double.parseDouble(tokens[4]);
        this.stop_lon = Double.parseDouble(tokens[5]);
        this.location_type = Integer.parseInt(tokens[6]);
    }

    @Override
    public String toString() {
        return stop_id + "," +stop_code + "," + stop_name + "," + stop_desc + ","  + stop_lat + "," + stop_lon + "," + location_type;
    }
}
