package com.ua.foxminded.dmgolub.formula1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RacerBuilderTest {
    
    @Nested
    class RacerBuilderNullTests {
        
        @Test
        void constructor_shoudThrowIllegalArgumentException_whenStartTimeMapIsNull() {            
            assertThrows(IllegalArgumentException.class, 
                () -> new RacerBuilder(null, new TreeMap<>()));
        }
        
        @Test
        void constructor_shoudThrowIllegalArgumentException_whenEndTimeMapIsNull() {

            assertThrows(IllegalArgumentException.class, 
                () -> new RacerBuilder(new TreeMap<>(), null));
        }
        
        @Test
        void build_shouldThrowIllegalArgumentException_whenInputStringIsNull() {
            RacerBuilder builder = new RacerBuilder(new TreeMap<>(), new TreeMap<>());
            
            assertThrows(IllegalArgumentException.class, () -> builder.build(null));
        }
    }
    
    @Nested
    class RacerBuilderNoTimeTests {
        
        @Test
        void build_shouldThrowIllegalArgumentException_whenNoStartTimeIsGiven() {
            Map<String, LocalDateTime> endTimeMap = new TreeMap<>();
            endTimeMap.put("ABB", LocalDateTime.of(
                LocalDate.of(2018, 8, 10), 
                LocalTime.of(0, 1, 1, 111_000_000)
            ));
            RacerBuilder builder = new RacerBuilder(new TreeMap<>(), endTimeMap);
            
            assertThrows(IllegalArgumentException.class, () -> builder.build("ABB_Name One_Team"));
        }
        
        @Test
        void build_shouldThrowIllegalArgumentException_whenNoFinishTimeIsGiven() {
            Map<String, LocalDateTime> startTimeMap = new TreeMap<>();
            startTimeMap.put("ABB", LocalDateTime.of(
                LocalDate.of(2018, 8, 10), 
                LocalTime.of(0, 1, 1, 111_000_000)
            ));
            RacerBuilder builder = new RacerBuilder(startTimeMap, new TreeMap<>());
            
            assertThrows(IllegalArgumentException.class, () -> builder.build("ABB_Name One_Team"));
        }
    }

    @Test
    void build_shoudThrowIllegalArgumentException_whenInputStringDoesNotMatchDefaultPattern() {
        RacerBuilder builder = new RacerBuilder(new TreeMap<>(), new TreeMap<>());
        
        assertThrows(IllegalArgumentException.class, () -> builder.build("some string"));
    }
    
    @Test
    void build_shouldBuildACorrectRacer_whenCorrectParametersAreGiven() {
        Racer expected = new Racer("ABB", "Name One", "Team", Duration.ofMinutes(1));

        Map<String, LocalDateTime> startTimeMap = new TreeMap<>();
        LocalDateTime startTime = LocalDateTime.of(
            LocalDate.of(2021, 5, 5), 
            LocalTime.of(0, 1, 1, 111_000_000)
        );
        startTimeMap.put("ABB", startTime);
        Map<String, LocalDateTime> endTimeMap = new TreeMap<>();
        LocalDateTime endTime = LocalDateTime.of(
            LocalDate.of(2021, 5, 5), 
            LocalTime.of(0, 2, 1, 111_000_000)
        );
        endTimeMap.put("ABB", endTime);
        RacerBuilder builder = new RacerBuilder(startTimeMap, endTimeMap);
        
        assertEquals(expected, builder.build("ABB_Name One_Team"));
    }
}