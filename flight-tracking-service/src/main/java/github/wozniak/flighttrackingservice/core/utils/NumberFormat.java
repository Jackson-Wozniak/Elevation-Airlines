package github.wozniak.flighttrackingservice.core.utils;

public class NumberFormat {
    public static String format(long num){
        return "";
    }

    public static String currency(double num){
        return "";
    }

    public static String percent(double percent){
        return (Math.round(percent * 100.00) / 100.00) + "%";
    }
}
