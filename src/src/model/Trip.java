package model;

public class Trip {
    private String id;
    private String source;
    private String destination;
    private String date;
    private String time;

    public Trip(String id, String source, String destination, String date, String time) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.time = time;
    }

    public String getId() { return id; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public String getDate() { return date; }
    public String getTime() { return time; }

    public String toCSV() {
        return String.join(",", escape(id), escape(source), escape(destination), escape(date), escape(time));
    }

    public static Trip fromCSV(String line) {
        String[] p = line.split(",", -1);
        if (p.length < 5) return null;
        return new Trip(unescape(p[0]), unescape(p[1]), unescape(p[2]), unescape(p[3]), unescape(p[4]));
    }

    private static String escape(String s) {
        return s.replace("\n", " ").replace(",", "⸴");
    }

    private static String unescape(String s) {
        return s.replace("⸴", ",");
    }

    @Override
    public String toString() {
        return source + " → " + destination + " (" + date + " " + time + ")";
    }
}
