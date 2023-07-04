package github.wozniak.flighttrackingservice.utils;

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

    public static String hoursToHHMM(double hours){
        int roundHour = (int) Math.floor(hours);
        if(roundHour == 0){
            int minutes = (int) Math.ceil(hours * 60);
            return "00:" + (minutes < 10 ? "0" + minutes : minutes);
        }
        int minutes = (int) Math.ceil((hours - roundHour) * 60);
        return String.format("%02d:%02d", roundHour, minutes);
    }

    //returns time in format hh:mm
    public static LocalTime createTimeOfFlight(){
        int hour = random.nextInt(24);
        int minute = random.nextBoolean() ? 30 : 0;
        return LocalTime.of(hour, minute);
    }

    public static String of(LocalDate date, LocalTime time){
        return LocalDateTime.of(date, time).format(dateTimeFormatter);
    }

    public static String format(LocalDateTime dateTime){
        return dateTime.format(dateTimeFormatter);
    }

    public static List<LocalDate> allDatesInRange(LocalDate start, LocalDate end){
        long numOfDaysBetween = ChronoUnit.DAYS.between(start, end);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(start::plusDays)
                .collect(Collectors.toList());
    }
}
