public class DatabaseMigration {
    public String id;
    public String state;
    public String type;
    public String host;
    public Long timestamp;

    public DatabaseMigration(String id, String state, String type, String host, Long timestamp) {
        this.id = id;
        this.state = state;
        this.type = type;
        this.host = host;
        this.timestamp = timestamp;
    }


}
