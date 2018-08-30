public class Migration {
    public String id;
    public String duration;
    public String type;
    public String host;
    public Boolean alert;

    public Migration(String id, String duration, String type, String host, Boolean alert) {
        this.id = id;
        this.duration = duration;
        this.type = type;
        this.host = host;
        this.alert = alert;
    }
}
