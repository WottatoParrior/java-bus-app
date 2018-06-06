package storage;

import static basics.HashMaps.allRoutes;
import static basics.HashMaps.allStopTimes;
import static basics.HashMaps.allStops;
import static basics.HashMaps.allTrips;
import static basics.HashMaps.queriedTrips;
import basics.Route;
import basics.Stop;
import basics.StopTimes;
import basics.Trip;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {

    private static Connection conn;
    private static Statement st;
    private static ResultSet rs;

    //Establishing Connection with Database
    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false", "root", "0december");
            st = conn.createStatement();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    //Creating all Database Tables for each hashmap 
    public static void CreateTable() {
        
        PreparedStatement prepStat = null;

        String CreateSQLStops = "CREATE TABLE Stops("
                + "STOP_ID INT(10),"
                + "STOP_CODE INT(10),"
                + "STOP_NAME VARCHAR(100),"
                + "STOP_DESC VARCHAR(100),"
                + "STOP_LAT FLOAT(13,8),"
                + "STOP_LON FLOAT(13,8),"
                + "LOCATION_TYPE INT(1),"
                + "CONSTRAINT StopsPK PRIMARY KEY (STOP_ID)"
                + ")";

        String CreateSQLRoutes = "CREATE TABLE Routes("
                + "ROUTE_ID VARCHAR(10),"
                + "ROUTE_SHORT_NAME VARCHAR(5),"
                + "ROUTE_LONG_NAME VARCHAR(100),"
                + "ROUTE_TYPE INT(1),"
                + "ROUTE_COLOR VARCHAR(10),"
                + "ROUTE_TEXT_COLOR VARCHAR(10),"
                + "CONSTRAINT RoutesPK PRIMARY KEY (ROUTE_ID)"
                + ")";

        String CreateSQLTrips = "CREATE TABLE Trips("
                + "ROUTE_ID VARCHAR(10),"
                + "SERVICE_ID VARCHAR(100),"
                + "TRIP_ID VARCHAR(100) PRIMARY KEY,"
                + "TRIP_HEADSIGN VARCHAR(100),"
                + "DIRECTION_ID INT(1),"
                + "TRIP_REPEATS INT(38),"
                + "CONSTRAINT TripsFK "
                + "FOREIGN KEY (ROUTE_ID) "
                + "REFERENCES Routes (ROUTE_ID)"
                + ")";

        String CreateSQLStopTimes = "CREATE TABLE StopTimes ("
                + "TRIP_ID VARCHAR(100),"
                + "STOP_ID INT(10),"
                + "STOP_SEQUENCE INT(5),"
                + "PICKUP_TYPE INT(1),"
                + "DROP_OFF_TYPE INT(1),"
                + "PRIMARY KEY (TRIP_ID, STOP_ID),"
                + "CONSTRAINT StopTimesFK FOREIGN KEY (STOP_ID) REFERENCES STOPS(STOP_ID)"
                + ")";

        try {
            System.out.print("Creating Stops Table...");
            prepStat = conn.prepareStatement(CreateSQLStops);
            prepStat.executeUpdate();
            System.out.println("Done!");

            System.out.print("Creating Routes Table...");
            prepStat = conn.prepareStatement(CreateSQLRoutes);
            prepStat.executeUpdate();
            System.out.println("Done!");

            System.out.print("Creating Trips Table...");
            prepStat = conn.prepareStatement(CreateSQLTrips);
            prepStat.executeUpdate();
            System.out.println("Done!");

            System.out.print("Creating StopTimes Table...");
            prepStat = conn.prepareStatement(CreateSQLStopTimes);
            prepStat.executeUpdate();
            System.out.println("Done!");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
    public static void storeAllRoutes(Route routes) {
        try {
            PreparedStatement pst;
            pst = conn.prepareStatement("INSERT INTO Routes VALUES(?,?,?,?,?,?)");
            pst.setString(1, routes.getRoute_id());
            pst.setString(2, routes.getRoute_short_name());
            pst.setString(3, routes.getRoute_long_name());
            pst.setInt(4, routes.getRoute_type());
            pst.setString(5, routes.getRoute_color());
            pst.setString(6, routes.getRoute_text_color());
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Inserting content of Trips Table
    public static void storeAllTrips(Trip trips) {
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO Trips VALUES(?,?,?,?,?,?)");
            pst.setString(1, trips.getRoute_id());
            pst.setString(2, trips.getService_id());
            pst.setString(3, trips.getTrip_id());
            pst.setString(4, trips.getTrip_headsign());
            pst.setInt(5, trips.getDirection_id());
            pst.setInt(6, trips.getTrip_repeats());
            pst.execute();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Inserting content of Stops Table
    public static void storeAllStops(Stop stops) {
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO Stops VALUES(?,?,?,?,?,?,?)");
            pst.setString(1, stops.getStop_id());
            pst.setInt(2, stops.getStop_code());
            pst.setString(3, stops.getStop_name());
            pst.setString(4, stops.getStop_desc());
            pst.setDouble(5, stops.getStop_lat());
            pst.setDouble(6, stops.getStop_lon());
            pst.setInt(7, stops.getLocation_type());
            pst.execute();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Inserting content of StopTimes Table    
    public static void storeAllStopTimes(StopTimes stopTimes) {
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO StopTimes VALUES(?,?,?,?,?)");
            pst.setString(1, stopTimes.getTrip_id());
            pst.setString(2, stopTimes.getStop_id());
            pst.setInt(3, stopTimes.getStop_sequence());
            pst.setInt(4, stopTimes.getPickup_type());
            pst.setInt(5, stopTimes.getDrop_off_type());
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void DataBaseFiller() {
  

        for (String key : allRoutes.keySet()) {
            
            
            Route currRoute = allRoutes.get(key);
            DB.storeAllRoutes(currRoute);
        }

        for (String key : allTrips.keySet()) {
           
            
            Trip currTrip = allTrips.get(key);
            DB.storeAllTrips(currTrip);
        }

        for (String key : allStops.keySet()) {
            
            Stop currStop = allStops.get(key);
            DB.storeAllStops(currStop);
        }

        for (String key : allStopTimes.keySet()) {
           
            StopTimes currStopTime = allStopTimes.get(key);
            DB.storeAllStopTimes(currStopTime);
        }
        System.out.println("Done ! :( i will miss you");
    }

    
    public static ArrayList<Route> getAllRoutes() {
        ArrayList<Route> allRoutes = new ArrayList<Route>();
        try {
            java.sql.Statement stmnt = conn.createStatement();
            ResultSet result = stmnt.executeQuery("SELECT * FROM Routes"); 
            while (result.next()) {
                Route routes = new Route(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4),
                        result.getString(5),
                        result.getString(6));
                allRoutes.add(routes);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return allRoutes;
    }

    //Printing Trips Table
    public static ArrayList<Trip> getAllTrips() {
        ArrayList<Trip> allTrips = new ArrayList<Trip>();
        try {
            java.sql.Statement stmnt = conn.createStatement();
            ResultSet result = stmnt.executeQuery("SELECT * FROM Trips");
            while (result.next()) {
                Trip trips = new Trip(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getInt(5),
                        result.getInt(6));
                allTrips.add(trips);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allTrips;
    }

    //Printing Stops Table
    public static ArrayList<Stop> getAllStops() {
        ArrayList<Stop> allStops = new ArrayList<Stop>();
        try {
            java.sql.Statement stmnt = conn.createStatement();
            ResultSet result = stmnt.executeQuery("SELECT * FROM Stops");
            while (result.next()) {
                Stop stops = new Stop(
                        result.getString(1),
                        result.getInt(2),
                        result.getString(3),
                        result.getString(4),
                        result.getDouble(5),
                        result.getDouble(6),
                        result.getInt(7));
                allStops.add(stops);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allStops;
    }

    //Printing StopTimes Table
    public static ArrayList<StopTimes> getAllStopTimes() {
        ArrayList<StopTimes> allStopTImes = new ArrayList<StopTimes>();
        try {
            java.sql.Statement stmnt = conn.createStatement();
            ResultSet result = stmnt.executeQuery("SELECT * FROM StopTimes");
            while (result.next()) {
                StopTimes stopTimes = new StopTimes(
                        result.getString(1),
                        result.getString(2));
                allStopTImes.add(stopTimes);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allStopTImes;
    }

    //Emptying All Tables Content
    public static void clearAllTables() {
        try {
            java.sql.Statement st = conn.createStatement();

            st.executeUpdate("DELETE FROM StopTimes");
            st.executeUpdate("DELETE FROM Stops");
            st.executeUpdate("DELETE FROM Trips");
            st.executeUpdate("DELETE FROM Routes");
            st.close();

            System.out.println("All tables cleared!");
        } catch (SQLException ex) {
            
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Dropping All Tables
    public static void dropAllTables() {
        try {

            java.sql.Statement st = conn.createStatement();

            st.executeUpdate("DROP TABLE StopTimes");
            st.executeUpdate("DROP TABLE Trips");
            st.executeUpdate("DROP TABLE Routes");
            st.executeUpdate("DROP TABLE Stops");
            st.close();

            System.out.println("All tables dropped!");
        } catch (SQLException ex) {
           

            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static LinkedHashMap<String,String> fetchFromDb(String k) throws SQLException {
     
             LinkedHashMap<String, String> findings = new LinkedHashMap<String, String>();
            PreparedStatement stm = conn.prepareStatement("Select * from stops where STOP_NAME LIKE ?");
            stm.setString(1,"%"+ k + "%");
            ResultSet results = stm.executeQuery();
            
            while (results.next()) {
                
                String stopId = results.getString("STOP_ID");
                String stopCode = results.getString("STOP_CODE");
                String stopName = results.getString("STOP_NAME");
                String stopDesc = results.getString("STOP_DESC");
                System.out.println(stopCode + "\t" + stopName
                        + "\t" + stopDesc);
                findings.put(stopId, stopCode + "-" + stopName + "-" + stopDesc);
            }
            
            return findings;
      
        
        
        
    }
    public static ArrayList<String> findRoute(String routeName) throws SQLException{
        
        ArrayList<String> routes = new ArrayList<String>();
        PreparedStatement stm = conn.prepareStatement("Select * from routes where ROUTE_LONG_NAME LIKE ?");
        stm.setString(1, "%" + routeName + "%");
        ResultSet results = stm.executeQuery();
        while (results.next()) {
            String routeId = results.getString("ROUTE_ID");
            String routeShName = results.getString("ROUTE_SHORT_NAME");
            String routeLonName = results.getString("ROUTE_LONG_NAME");
            int type = results.getInt("ROUTE_TYPE");
            String color = results.getString("ROUTE_COLOR");
            String textcolor = results.getString("ROUTE_TEXT_COLOR");
            
            routes.add(routeId + "--" +routeShName + "--" + routeLonName + "--" + type + "--" + color + "--" + textcolor);
        }
        
    
    
        return routes;
    }
    public static void insertInDb(String rid, String rnames, String rnamel, int rtype, String color, String tcolor) throws SQLException {
        PreparedStatement stm = conn.prepareStatement("UPDATE routes SET ROUTE_SHORT_NAME=?, ROUTE_LONG_NAME=?, ROUTE_TYPE=?, ROUTE_COLOR=?, ROUTE_TEXT_COLOR=? WHERE ROUTE_ID=?");
        
        stm.setString(1, rnames);
        stm.setString(2, rnamel);
        stm.setInt(3, rtype);
        stm.setString(4, color);
        stm.setString(5, tcolor);
        stm.setString(6, rid);
        stm.executeUpdate();
    }
    public static ArrayList<String> fetchFromDbInfo(String k) throws SQLException {
     
             ArrayList<String> findings = new ArrayList<String>();
            
            PreparedStatement stm = conn.prepareStatement("Select * from stoptimes where STOP_ID LIKE ?");
            stm.setString(1, k);
            ResultSet results = stm.executeQuery();
            while (results.next()) {
                String tripId = results.getString("TRIP_ID");
                findings.add(tripId);
            }
            return findings;    
        
    }
    public static ArrayList<String> fetchRoutes(String tripId) throws SQLException {
     
             ArrayList<String> routesFound = new ArrayList<String>();
            
            PreparedStatement stm = conn.prepareStatement("Select ROUTE_ID from trips where TRIP_ID = ?");
            stm.setString(1,tripId);
            ResultSet results = stm.executeQuery();
            while (results.next()) {
                String routeId = results.getString("ROUTE_ID");
                routesFound.add(routeId);
                
            }
            System.out.println(routesFound);
            return routesFound;    
        
    }
    
    public static String showRoutes(String routeId) throws SQLException {
     
            String routes = null;
            PreparedStatement stm = conn.prepareStatement("Select * from routes where ROUTE_ID = ?");
            stm.setString(1,routeId);
            ResultSet results = stm.executeQuery();
            while (results.next()) {
                String routeShortName = results.getString("ROUTE_SHORT_NAME");
                String routeLongName = results.getString("ROUTE_LONG_NAME");
                routes= routeShortName + "   " + routeLongName;
                
            }
            System.out.println(routes);
            return routes;    
        
    }
    //Disconnecting from Database
    public static void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection terminated!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}
