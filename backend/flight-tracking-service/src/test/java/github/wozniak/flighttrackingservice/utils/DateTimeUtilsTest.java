package github.wozniak.flighttrackingservice.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilsTest {

    @Test
    void formatTime(){
        assertEquals("00:30", DateTimeUtils.hoursToHHMM(0.5));
        assertEquals("02:30", DateTimeUtils.hoursToHHMM(2.5));
        assertEquals("12:26", DateTimeUtils.hoursToHHMM(12.43));
        assertEquals("116:50", DateTimeUtils.hoursToHHMM(116.83));
    }

    @Test
    void formatDate(){
        LocalDate first = LocalDate.of(2023, 7, 4);
        LocalDate second = LocalDate.of(2050, 12, 30);
        LocalDate third = LocalDate.of(2020, 1, 1);
        assertEquals("07/04/2023", DateTimeUtils.format(first));
        assertEquals("12/30/2050", DateTimeUtils.format(second));
        assertEquals("01/01/2020", DateTimeUtils.format(third));
    }

    @Test
    void formatDateTime(){
        LocalDateTime first = LocalDateTime.of(2023, 7, 4, 12, 59);
        LocalDateTime second = LocalDateTime.of(2050, 12, 30, 19, 40);
        LocalDateTime third = LocalDateTime.of(2020, 1, 1, 1, 9);
        assertEquals("07/04/2023 12:59", DateTimeUtils.format(first));
        assertEquals("12/30/2050 19:40", DateTimeUtils.format(second));
        assertEquals("01/01/2020 01:09", DateTimeUtils.format(third));
    }

    @Test
    void allDatesInRangeTest(){
        List<LocalDate> dates = DateTimeUtils.allDatesInRange(LocalDate.now(), LocalDate.now().plusDays(7));
        assertEquals(7, dates.size());
        for(int i = 0; i < 7; i++){
            assertEquals(LocalDate.now().plusDays(i), dates.get(i));
        }
    }

    @Test
    void liveFlightTest(){
        LocalDateTime takeOffTime = LocalDateTime.now().minusHours(5);

        //5 hours left in flight (live)
        assertTrue(DateTimeUtils.isLiveFlight(takeOffTime, 10.00));

        //flight landed 3 hours ago (not live)
        assertFalse(DateTimeUtils.isLiveFlight(takeOffTime, 2.00));

        //flight landed 5 minutes ago (not live)
        assertFalse(DateTimeUtils.isLiveFlight(takeOffTime, 4.92));

        //5 minutes left in flight (live)
        assertTrue(DateTimeUtils.isLiveFlight(takeOffTime, 5.08));

        //flight is currently landing (not live)
        assertFalse(DateTimeUtils.isLiveFlight(takeOffTime, 5.00));
    }

    @Test
    void isValidDateFormat(){
        assertTrue(DateTimeUtils.isValid("01/12/2023"));
        assertFalse(DateTimeUtils.isValid("13/12/2023"));
        assertFalse(DateTimeUtils.isValid("01-12-2023"));
    }

    @Test
    void toSQLDateFormat(){
        assertEquals("2023-07-04", DateTimeUtils.stringToSQLDate("07/04/2023").toString());
        assertThrows(DateTimeParseException.class, () -> DateTimeUtils.stringToSQLDate("000/01/1"));
    }
}