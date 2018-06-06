package basics;

public class StopTimes {

    private String trip_id;
    private String stop_id;
    private int stop_sequence;
    private int pickup_type;
    private int drop_off_type;
    private String stoptime_id;

    public String getStoptime_id() {
        return stoptime_id;
    }

    public void setStoptime_id(String stoptime_id) {
        this.stoptime_id = stoptime_id;
    }

    public int getStop_sequence() {
        return stop_sequence;
    }

    public void setStop_sequence(int stop_sequence) {
        this.stop_sequence = stop_sequence;
    }

    public int getPickup_type() {
        return pickup_type;
    }

    public void setPickup_type(int pickup_type) {
        this.pickup_type = pickup_type;
    }

    public int getDrop_off_type() {
        return drop_off_type;
    }

    public void setDrop_off_type(int drop_off_type) {
        this.drop_off_type = drop_off_type;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getStop_id() {
        return stop_id;
    }

    public void setStop_id(String stop_id) {
        this.stop_id = stop_id;
    }

    public StopTimes(String trip_id, String stop_id) {
        this.trip_id = trip_id;
        this.stop_id = stop_id;
    }

    public StopTimes(String row) {
        String[] TokensNew = row.split("-");
        String[] Tokens = row.split(",");
        this.stoptime_id = TokensNew[2] + Tokens[1].substring(0, Tokens[1].length());
        this.trip_id = Tokens[0].substring(0, Tokens[0].length());
        this.stop_id = Tokens[1].substring(0, Tokens[1].length());
        this.stop_sequence = Integer.parseInt(Tokens[2]);
        this.pickup_type = Integer.parseInt(Tokens[3]);
        this.drop_off_type = Integer.parseInt(Tokens[4]);
    }

    @Override
    public String toString() {
        return trip_id + "," + stop_id;
    }
}
