package com.ua.foxminded.dmgolub.formula1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TimeMapBuilderTest {
    
    private static final String ABB_AND_TIME_REG_EX = "\\D{3}\\d{4}(-\\d{2}){2}_(\\d{2}:){2}\\d{2}.\\d{1,3}";
    
    private Parser<Pair<String, LocalDateTime>> parser = new TimeMapEntryParser(ABB_AND_TIME_REG_EX);
    private TimeMapBuilder timeMapBuilder = new TimeMapBuilder(parser);

    
    @Nested
    class TimeMapBuilderNullTests {
        
        @Test
        void constructor_shouldThrowIllegalArgumentException_whenParserIsNull() {
            assertThrows(IllegalArgumentException.class, () -> new TimeMapBuilder(null));
        }
        
        @Test
        void build_shouldThrowIllegalArgumentException_whenInputListIsNull() {
            assertThrows(IllegalArgumentException.class, () -> timeMapBuilder.build(null));
        }
    }

    @Test
    void build_shouldReturnAnEmptyMap_whenInputListIsEmpty() {
        Map<String, LocalDateTime> expected = new TreeMap<>();
        
        List<String> emptyList = new ArrayList<>();
        
        assertEquals(expected, timeMapBuilder.build(emptyList));
    }
    
    @Test
    void build_shouldReturnACorrectMap_whenInputListIsNotEmptyAndMatchesTheGivenPattern() {
        Map<String, LocalDateTime> expected = new TreeMap<>();
        expected.put("SVF", LocalDateTime.of(LocalDate.of(2018, 5, 24), LocalTime.of(12, 2, 58, 917_000_000)));
        expected.put("NHR", LocalDateTime.of(LocalDate.of(2018, 5, 24), LocalTime.of(12, 2, 49, 914_000_000)));
        expected.put("FAM", LocalDateTime.of(LocalDate.of(2018, 5, 24), LocalTime.of(12, 13, 4, 512_000_000)));
        expected.put("KRF", LocalDateTime.of(LocalDate.of(2018, 5, 24), LocalTime.of(12, 3, 1, 250_000_000)));
        expected.put("SVM", LocalDateTime.of(LocalDate.of(2018, 5, 24), LocalTime.of(12, 18, 37, 735_000_000)));
        expected.put("MES", LocalDateTime.of(LocalDate.of(2018, 5, 24), LocalTime.of(12, 04, 45, 513_000_000)));
        
        List<String> input = new ArrayList<>();
        input.add("SVF2018-05-24_12:02:58.917");
        input.add("NHR2018-05-24_12:02:49.914");
        input.add("FAM2018-05-24_12:13:04.512");
        input.add("KRF2018-05-24_12:03:01.250");
        input.add("SVM2018-05-24_12:18:37.735");
        input.add("MES2018-05-24_12:04:45.513");
        
        assertEquals(expected, timeMapBuilder.build(input));
    }   
}