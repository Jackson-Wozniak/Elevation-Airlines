package github.wozniak.flighttrackingservice.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeFormatTest {

    @Test
    void formatTime(){
        assertEquals("00:30", TimeFormat.hoursToHHMM(0.5));
        assertEquals("02:30", TimeFormat.hoursToHHMM(2.5));
        assertEquals("12:26", TimeFormat.hoursToHHMM(12.43));
        assertEquals("116:50", TimeFormat.hoursToHHMM(116.83));
    }
}