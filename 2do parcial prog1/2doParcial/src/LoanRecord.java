
public class LoanRecord {
    private String user;
    private String timestamp; 

    public LoanRecord(String user, String timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    public String getUser() {
        return user;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "LoanRecord{user='" + user + "', ts='" + timestamp + "'}";
    }
}
