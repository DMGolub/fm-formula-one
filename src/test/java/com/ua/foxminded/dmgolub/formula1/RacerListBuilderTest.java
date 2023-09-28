package com.ua.foxminded.dmgolub.formula1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RacerListBuilderTest {
    
    private RacerListBuilder racerListBuilder = new RacerListBuilder();
    
    @Nested
    class RacerListBuilderNullTests {
        
        @Test
        void build_shouldThrowIllegalArgumentException_when_descriptionListIsNull() {
            Map<String, LocalDateTime> startTimeMap = new TreeMap<>(); 
            Map<String, LocalDateTime> endTimeMap = new TreeMap<>();
            
            assertThrows(IllegalArgumentException.class, () -> racerListBuilder.build(null, startTimeMap, endTimeMap));
        }
        
        @Test
        void build_shouldThrowIllegalArgumentException_when_startTimeMapIsNull() {
            List<String> descriptions = new ArrayList<>();
            Map<String, LocalDateTime> endTimeMap = new TreeMap<>();
            
            assertThrows(IllegalArgumentException.class, () -> racerListBuilder.build(descriptions, null, endTimeMap));
        }
        
        @Test
        void build_shouldThrowIllegalArgumentException_when_endTimeMapIsNull() {
            List<String> descriptions = new ArrayList<>();
            Map<String, LocalDateTime> startTimeMap = new TreeMap<>();
            
            assertThrows(IllegalArgumentException.class, () -> racerListBuilder.build(descriptions, startTimeMap, null));
        }
    }

    @Test
    void build_shouldReturnAListOfRacers_whenCorrectInputDataIsGiven() {
        List<Racer> expected = new ArrayList<>();
        expected.add(new Racer("ABO", "Name One", "Team One", Duration.ofMinutes(1)));
        expected.add(new Racer("ABT", "Name Two", "Team Two", Duration.ofMinutes(2)));
        
        List<String> descriptions = Arrays.asList("ABO_Name One_Team One", "ABT_Name Two_Team Two");
        Map<String, LocalDateTime> startTimeMap = new TreeMap<>();
        startTimeMap.put("ABO", LocalDateTime.of(LocalDate.of(2018, 8, 10), LocalTime.of(0, 1, 0)));
        startTimeMap.put("ABT", LocalDateTime.of(LocalDate.of(2018, 8, 10), LocalTime.of(0, 1, 0)));
        
        Map<String, LocalDateTime> endTimeMap = new TreeMap<>();
        endTimeMap.put("ABO", LocalDateTime.of(LocalDate.of(2018, 8, 10), LocalTime.of(0, 2, 0)));
        endTimeMap.put("ABT", LocalDateTime.of(LocalDate.of(2018, 8, 10), LocalTime.of(0, 3, 0)));
        
        assertEquals(expected, racerListBuilder.build(descriptions, startTimeMap, endTimeMap));
    }
    
    @Test
    void build_shouldSortRacersByLapDuration_whenInputDataIsDisordered() {
        List<Racer> expected = new ArrayList<>();
        expected.add(new Racer("ABO", "Name One", "Team One", Duration.ofMinutes(1)));
        expected.add(new Racer("ABT", "Name Two", "Team Two", Duration.ofMinutes(2)));
        
        List<String> descriptions = Arrays.asList("ABT_Name Two_Team Two", "ABO_Name One_Team One");
        Map<String, LocalDateTime> startTimeMap = new TreeMap<>();
        startTimeMap.put("ABT", LocalDateTime.of(LocalDate.of(2018, 8, 10), LocalTime.of(0, 1, 0)));
        startTimeMap.put("ABO", LocalDateTime.of(LocalDate.of(2018, 8, 10), LocalTime.of(0, 1, 0)));

        Map<String, LocalDateTime> endTimeMap = new TreeMap<>();
        endTimeMap.put("ABT", LocalDateTime.of(LocalDate.of(2018, 8, 10), LocalTime.of(0, 3, 0)));
        endTimeMap.put("ABO", LocalDateTime.of(LocalDate.of(2018, 8, 10), LocalTime.of(0, 2, 0)));

        assertEquals(expected, racerListBuilder.build(descriptions, startTimeMap, endTimeMap));
    }

}
