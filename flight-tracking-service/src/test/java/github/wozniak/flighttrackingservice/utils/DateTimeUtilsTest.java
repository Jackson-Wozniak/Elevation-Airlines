package github.wozniak.flighttrackingservice.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilsTest {

    @Test
    void hoursToHHMMTest(){
        assertEquals("00:30", DateTimeUtils.hoursToHHMM(0.5));
        assertEquals("02:30", DateTimeUtils.hoursToHHMM(2.5));
        assertEquals("12:26", DateTimeUtils.hoursToHHMM(12.43));
        assertEquals("116:50", DateTimeUtils.hoursToHHMM(116.83));
    }

    @Test
    void formatTest(){
        //Date
        LocalDate d1 = LocalDate.of(2023, 7, 4);
        LocalDate d2 = LocalDate.of(2050, 12, 30);
        LocalDate d3 = LocalDate.of(2020, 1, 1);
        assertEquals("07/04/2023", DateTimeUtils.format(d1));
        assertEquals("12/30/2050", DateTimeUtils.format(d2));
        assertEquals("01/01/2020", DateTimeUtils.format(d3));

        //DateTime
        LocalDateTime dt1 = LocalDateTime.of(2023, 7, 4, 12, 59);
        LocalDateTime dt2 = LocalDateTime.of(2050, 12, 30, 19, 40);
        LocalDateTime dt3 = LocalDateTime.of(2020, 1, 1, 1, 9);
        assertEquals("07/04/2023 12:59", DateTimeUtils.format(dt1));
        assertEquals("12/30/2050 19:40", DateTimeUtils.format(dt2));
        assertEquals("01/01/2020 01:09", DateTimeUtils.format(dt3));

        //Time
        LocalTime t1 = LocalTime.of(12, 59);
        LocalTime t2 = LocalTime.of(19, 40);
        LocalTime t3 = LocalTime.of(1, 9);
        assertEquals("12:59", DateTimeUtils.format(t1));
        assertEquals("19:40", DateTimeUtils.format(t2));
        assertEquals("01:09", DateTimeUtils.format(t3));
    }

    @Test
    void allDatesInRangeTest(){
        List<LocalDate> dates = DateTimeUtils.allDatesInRange(LocalDate.now(), LocalDate.now().plusDays(7), false);
        assertEquals(7, dates.size());
        for(int i = 0; i < 7; i++){
            assertEquals(LocalDate.now().plusDays(i), dates.get(i));
        }
    }

    @Test
    void isLiveFlightTest(){
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
    void isValidTest(){
        assertTrue(DateTimeUtils.isValid("01/12/2023"));
        assertFalse(DateTimeUtils.isValid("13/12/2023"));
        assertFalse(DateTimeUtils.isValid("01-12-2023"));
    }

    @Test
    void stringToSQLDateTest(){
        assertEquals("2023-07-04", DateTimeUtils.stringToSQLDate("07/04/2023").toString());
        assertThrows(DateTimeParseException.class, () -> DateTimeUtils.stringToSQLDate("000/01/1"));
    }

    @Test
    void toDateTest(){
        assertThrows(DateTimeParseException.class, () -> DateTimeUtils.toDate("12-01-10"));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtils.toDate("13/01/10"));
        assertDoesNotThrow(() -> DateTimeUtils.toDate("12/01/10"));
        assertEquals(LocalDate.of(23, 11, 1), DateTimeUtils.toDate("11/01/23"));
    }

    @Test
    void createTimeOfFlightTest(){
        for(int i = 0; i < 100; i++){
            LocalTime tUnrestricted = DateTimeUtils.createTimeOfFlight();
            LocalTime tEarliest = DateTimeUtils.createTimeOfFlight(12);
            LocalTime tLatest = DateTimeUtils.createTimeOfFlight(12, true);

            assertNotNull(tUnrestricted);
            assertFalse(tEarliest.isBefore(LocalTime.of(12, 0)));
            assertTrue(tLatest.isBefore(LocalTime.of(13, 0)));
        }
    }

    @Test
    void ofTest(){
        LocalDate date = LocalDate.of(2023, 1, 1);
        LocalTime time = LocalTime.of(1, 0);
        assertEquals("01/01/2023 01:00", DateTimeUtils.of(date, time));
    }
}