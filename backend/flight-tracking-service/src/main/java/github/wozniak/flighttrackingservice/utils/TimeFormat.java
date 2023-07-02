package github.wozniak.flighttrackingservice.utils;

public class TimeFormat {

    public static String hoursToHHMM(double hours){
        int roundHour = (int) Math.floor(hours);
        if(roundHour == 0){
            int minutes = (int) Math.ceil(hours * 60);
            return "00:" + (minutes < 10 ? "0" + minutes : minutes);
        }
        int minutes = (int) Math.ceil((hours - roundHour) * 60);
        return String.format("%02d:%02d", roundHour, minutes);
    }
}
