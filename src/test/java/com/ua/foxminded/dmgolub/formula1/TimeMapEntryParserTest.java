package com.ua.foxminded.dmgolub.formula1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class TimeMapEntryParserTest {
    
    private static final String ABB_AND_TIME_REG_EXP = "\\D{3}\\d{4}(-\\d{2}){2}_(\\d{2}:){2}\\d{2}.\\d{1,3}";
    private TimeMapEntryParser parser = new TimeMapEntryParser(ABB_AND_TIME_REG_EXP);
    
    @Test
    void parserConstructor_shouldThrowIllegalArgumentException_whenRegularExpressionIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new TimeMapEntryParser(null));
    }
    
    @Test
    void parse_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(null));
    }
    
    @Test
    void parse_shouldThrowIllegalArgumentException_whenInputDoesNotMatchTheGivenPattern() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse("some random string"));
    }

    @Test
    void parse_shouldReturnACorrectMapEntry_whenCorrectInputStringIsGiven() {
        LocalDateTime dateTime = LocalDateTime.of(
            LocalDate.of(2018, 5, 24),
            LocalTime.of(12, 02, 58, 917_000_000)
        );
        Pair<String, LocalDateTime> expected = new Pair<>("SVF", dateTime);
        
        assertEquals(expected, parser.parse("SVF2018-05-24_12:02:58.917"));
    }
}