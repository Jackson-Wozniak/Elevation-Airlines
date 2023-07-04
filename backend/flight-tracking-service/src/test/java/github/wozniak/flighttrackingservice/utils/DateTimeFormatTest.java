package github.wozniak.flighttrackingservice.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeFormatTest {

    @Test
    void formatTime(){
        assertEquals("00:30", DateTimeFormat.hoursToHHMM(0.5));
        assertEquals("02:30", DateTimeFormat.hoursToHHMM(2.5));
        assertEquals("12:26", DateTimeFormat.hoursToHHMM(12.43));
        assertEquals("116:50", DateTimeFormat.hoursToHHMM(116.83));
    }

    @Test
    void allDatesInRangeTest(){
        List<LocalDate> dates = DateTimeFormat.allDatesInRange(LocalDate.now(), LocalDate.now().plusDays(7));
        assertEquals(7, dates.size());
        for(int i = 0; i < 7; i++){
            assertEquals(LocalDate.now().plusDays(i), dates.get(i));
        }
    }
}