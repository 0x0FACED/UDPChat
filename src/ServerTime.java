import java.text.SimpleDateFormat;
public class ServerTime {
    private static java.util.Date time;
    private static String dtime;
    private static SimpleDateFormat dt1;

    public static java.util.Date getTime() {
        return time;
    }

    public static void setTime(java.util.Date time) {
        ServerTime.time = time;
    }

    public static String getDtime() {
        return dtime;
    }

    public static void setDtime(String dtime) {
        ServerTime.dtime = dtime;
    }

    public static SimpleDateFormat getDt1() {
        return dt1;
    }

    public static void setDt1(SimpleDateFormat dt1) {
        ServerTime.dt1 = dt1;
    }
}
