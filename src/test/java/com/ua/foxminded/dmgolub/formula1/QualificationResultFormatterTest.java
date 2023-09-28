package com.ua.foxminded.dmgolub.formula1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class QualificationResultFormatterTest {
    
    @SuppressWarnings("unchecked")
    private Formatter<List<Racer>> formatterMock = Mockito.mock(Formatter.class);
    private FormatterDecorator<List<Racer>> formatter = new QualificationResultFormatter(formatterMock, 2);
    
    @Test
    void formatterConstructor_shouldThrowIllegalArgumentException_whenDelimiterPositionIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new QualificationResultFormatter(formatterMock, -1));
    }
    
    @Test
    void formatterConstructor_shouldThrowIllegalArgumentException_whenDelimiterPositionIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new QualificationResultFormatter(formatterMock, 0));
    }
    
    @Test
    void formatter_shouldUnderlineTheGivenRow_whenInputListSizeIsGreaterThanDilimiterPosition() {
        StringJoiner expected = new StringJoiner(System.lineSeparator());
        expected.add("1. Name|Team|1:01.000")
                .add("2. Name|Team|1:01.000")
                .add("---------------------")
                .add("3. Name|Team|1:01.000");
        
        List<Racer> racers = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            racers.add(new Racer("ABB", "Name", "Team", Duration.parse("PT1M1S")));
        }
        StringJoiner mockResult = new StringJoiner(System.lineSeparator());
        mockResult.add("1. Name|Team|1:01.000")
                  .add("2. Name|Team|1:01.000")
                  .add("3. Name|Team|1:01.000");
        Mockito.when(formatterMock.format(racers)).thenReturn(mockResult.toString());
        
        assertEquals(expected.toString(), formatter.format(racers));
    }
    
    @Test
    void formatter_shouldNotUnderline_whenInputListSizeIsEqualToDilimiterPosition() {
        StringJoiner expected = new StringJoiner(System.lineSeparator());
        expected.add("1. Name|Team|1:01.000")
                .add("2. Name|Team|1:01.000");
        
        List<Racer> racers = new ArrayList<>();
        for (int i = 0; i < 2; ++i) {
            racers.add(new Racer("ABB", "Name", "Team", Duration.parse("PT1M1S")));
        }
        Mockito.when(formatterMock.format(racers)).thenReturn(expected.toString());
        
        assertEquals(expected.toString(), formatter.format(racers));
    }
    
    @Test
    void formatter_shouldNotUnderline_whenInputListSizeIsLessThanDilimiterPosition() {
        String expected = "1. Name|Team|1:01.000";
        
        List<Racer> racers = new ArrayList<>();
        racers.add(new Racer("ABB", "Name", "Team", Duration.parse("PT1M1S")));
        Mockito.when(formatterMock.format(racers)).thenReturn(expected);
        
        assertEquals(expected.toString(), formatter.format(racers));
    }
}