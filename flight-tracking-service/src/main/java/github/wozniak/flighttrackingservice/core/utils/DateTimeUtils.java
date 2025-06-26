package github.wozniak.flighttrackingservice.core.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateTimeUtils {

    private static final Random random = new Random();
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter dateParser = DateTimeFormatter.ofPattern("M/d/y");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public static String hoursToHHMM(double hours){
        int roundHour = (int) Math.floor(hours);
        if(roundHour == 0){
            int minutes = (int) Math.ceil(hours * 60);
            return "00:" + (minutes < 10 ? "0" + minutes : minutes);
        }
        int minutes = (int) Math.ceil((hours - roundHour) * 60);
        return String.format("%02d:%02d", roundHour, minutes);
    }

    public static LocalDateTime plusHours(LocalDateTime time, double hours){
        int hrs = (int) Math.floor(hours);
        int mins = (int) Math.ceil((hours - hrs) * 60);
        return time.plusHours(hrs).plusMinutes(mins);
    }

    public static String hoursToHRMIN(double hours){
        int roundHour = (int) Math.floor(hours);
        if(roundHour == 0){
            int minutes = (int) Math.ceil(hours * 60);
            return minutes + "mins";
        }
        int minutes = (int) Math.ceil((hours - roundHour) * 60);
        return String.format("%02dhr %02dmins", roundHour, minutes);
    }

    //returns time in format hh:mm
    public static LocalTime createTimeOfFlight(){
        int hour = random.nextInt(20);
        int minute = random.nextBoolean() ? 30 : 0;
        return LocalTime.of(hour, minute);
    }
    public static LocalTime createTimeOfFlight(int earliestHour){
        if(earliestHour >= 23) return LocalTime.of(23, 59);
        int hour = random.nextInt(23 - earliestHour) + earliestHour;
        int minute = random.nextBoolean() ? 30 : 0;
        return LocalTime.of(hour, minute);
    }

    public static LocalTime createTimeOfFlight(int latestHour, boolean isRestricted){
        if(latestHour <= 0) return LocalTime.of(0, 0);
        int hour = random.nextInt(latestHour);
        int minute = random.nextBoolean() ? 30 : 0;
        return LocalTime.of(hour, minute);
    }


    public static String of(LocalDate date, LocalTime time){
        return LocalDateTime.of(date, time).format(dateTimeFormatter);
    }

    public static String format(LocalDateTime dateTime){
        return dateTime.format(dateTimeFormatter);
    }

    public static String format(LocalDate date){
        return dateFormatter.format(date);
    }

    public static String format(LocalTime date){
        return timeFormatter.format(date);
    }

    public static Date stringToSQLDate(String date){
        return Date.valueOf(LocalDate.parse(date, dateFormatter));
    }

    public static LocalDate toDate(String date){
        return LocalDate.parse(date, dateParser);
    }

    public static List<LocalDate> allDatesInRange(LocalDate start, LocalDate end, boolean isInclusive){
        long numOfDaysBetween = ChronoUnit.DAYS.between(start, end) + (isInclusive ? 1 : 0);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(start::plusDays)
                .collect(Collectors.toList());
    }

    public static boolean isLiveFlight(LocalDateTime takeOffTime, double flightHours) {
        long elapsedMinutes = (long) (flightHours * 60);
        LocalDateTime now = LocalDateTime.now();

        return now.isAfter(takeOffTime) && now.isBefore(takeOffTime.plusMinutes(elapsedMinutes));
    }

    public static boolean isValid(String date) {
        try {
            dateFormatter.parse(date);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static String expectedTimeOfArrival(LocalDateTime takeOffTime, double flightHours){
        long elapsedMinutes = (long) (flightHours * 60);
        return format(takeOffTime.plusMinutes(elapsedMinutes));
    }
}
