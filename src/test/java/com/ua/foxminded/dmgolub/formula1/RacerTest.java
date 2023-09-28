package com.ua.foxminded.dmgolub.formula1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RacerTest {
    
    @Nested
    class RacerNullTests {
        
        @Test
        void racerConstructor_shouldThrowIllegalArgumentException_whenAbbreviationIsNull() {
            assertThrows(IllegalArgumentException.class, () -> new Racer(null, "name", "team", Duration.ZERO));
        }
        
        @Test
        void racerConstructor_shouldThrowIllegalArgumentException_whenRacerNameIsNull() {
            assertThrows(IllegalArgumentException.class, () -> new Racer("ABB", null, "team", Duration.ZERO));
        }
        
        @Test
        void racerConstructor_shouldThrowIllegalArgumentException_whenTeamIsNull() {
            assertThrows(IllegalArgumentException.class, () -> new Racer("ABB", "name", null, Duration.ZERO));
        }
        
        @Test
        void racerConstructor_shouldThrowIllegalArgumentException_whenLapTimesListIsNull() {
            assertThrows(IllegalArgumentException.class, () -> new Racer("ABB", "name", "team", null));
        }
    }
    
    @Test
    void getFastestLap_shouldReturnTheGivenFastestLapDuration() {
        Duration expected = Duration.parse("PT1M2.345S");
        
        Racer racer = new Racer("ABB", "name", "team", expected);
        
        assertEquals(expected, racer.getFastestLap());
    }
}