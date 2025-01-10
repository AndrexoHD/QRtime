package QRtimePackage;

import java.time.LocalTime;

public class TimeObject {
    int hour;
    int minute;
    int second;

    private TimeObject(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public static TimeObject getTimeObject() {
        LocalTime localTime = LocalTime.now();
        String localTimeString = localTime.toString();
        String[] hourArray = localTimeString.split(":", 3);
        hourArray[2] = String.format("%.2s", hourArray[2]);
        int hour    = Integer.parseInt(hourArray[0]);
        int minute  = Integer.parseInt(hourArray[1]);
        int second  = Integer.parseInt(hourArray[2]);
        return new TimeObject(hour, minute, second);
    }
}
