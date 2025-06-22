import java.sql.Timestamp;

public class LogModel {
    private String logData;
    private Timestamp timestamp;
    private LogEnum logEnum;

    public LogModel(String logData, LogEnum logEnum) {
        this.logData = logData;
        this.logEnum = logEnum;
        this.timestamp = new Timestamp( System.currentTimeMillis() );
    }

    @Override
    public String toString() {
        return "{" +
                " timestamp=" + timestamp +
                ", logEnum=" + logEnum +
                ", logData='" + logData + '\'' +
                '}';
    }
}
