package com.ua.foxminded.dmgolub.formula1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import org.junit.jupiter.api.Test;

class StageResultFormatterTest {
    
    private StageResultFormatter formatter = new StageResultFormatter();

    @Test
    void formatter_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> formatter.format(null));
    }
    
    @Test
    void formatter_shouldReturnEmptyString_whenInputIsEmpty() {
        assertTrue(formatter.format(new ArrayList<Racer>()).isEmpty());
    }
    
    @Test
    void formatter_shouldReturnSingleLine_whenInputListContainsOneRacer() {
        String expected = "1. Name|Team|1:00.000";
        
        List<Racer> racers = new ArrayList<>();
        racers.add(new Racer("ABB", "Name", "Team", Duration.ofMinutes(1)));
        
        assertEquals(expected, formatter.format(racers));
    }
    
    @Test
    void formatter_shouldReturnTheCorrectResult_whenTheListFromTheTaskIsGiven() {
        StringJoiner expected = new StringJoiner(System.lineSeparator());
        expected.add("1.  Sebastian Vettel |FERRARI                  |1:04.415")
            .add("2.  Daniel Ricciardo |RED BULL RACING TAG HEUER|1:12.013")
            .add("3.  Valtteri Bottas  |MERCEDES                 |1:12.434")
            .add("4.  Lewis Hamilton   |MERCEDES                 |1:12.460")
            .add("5.  Stoffel Vandoorne|MCLAREN RENAULT          |1:12.463")
            .add("6.  Kimi Raikkonen   |FERRARI                  |1:12.639")
            .add("7.  Fernando Alonso  |MCLAREN RENAULT          |1:12.657")
            .add("8.  Sergey Sirotkin  |WILLIAMS MERCEDES        |1:12.706")
            .add("9.  Charles Leclerc  |SAUBER FERRARI           |1:12.829")
            .add("10. Sergio Perez     |FORCE INDIA MERCEDES     |1:12.848")
            .add("11. Romain Grosjean  |HAAS FERRARI             |1:12.930")
            .add("12. Pierre Gasly     |SCUDERIA TORO ROSSO HONDA|1:12.941")
            .add("13. Carlos Sainz     |RENAULT                  |1:12.950")
            .add("14. Esteban Ocon     |FORCE INDIA MERCEDES     |1:13.028")
            .add("15. Nico Hulkenberg  |RENAULT                  |1:13.065")
            .add("16. Brendon Hartley  |SCUDERIA TORO ROSSO HONDA|1:13.179")
            .add("17. Marcus Ericsson  |SAUBER FERRARI           |1:13.265")
            .add("18. Lance Stroll     |WILLIAMS MERCEDES        |1:13.323")
            .add("19. Kevin Magnussen  |HAAS FERRARI             |1:13.393");
        
        List<Racer> racers = Arrays.asList(
            new Racer("SVF", "Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")), 
            new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")), 
            new Racer("VBM", "Valtteri Bottas", "MERCEDES", Duration.parse("PT1M12.434S")), 
            new Racer("LHM", "Lewis Hamilton", "MERCEDES", Duration.parse("PT1M12.460S")),  
            new Racer("SVM", "Stoffel Vandoorne", "MCLAREN RENAULT", Duration.parse("PT1M12.463S")), 
            new Racer("KRF", "Kimi Raikkonen", "FERRARI", Duration.parse("PT1M12.639S")), 
            new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT", Duration.parse("PT1M12.657S")),
            new Racer("SSW", "Sergey Sirotkin", "WILLIAMS MERCEDES", Duration.parse("PT1M12.706S")),
            new Racer("CLS", "Charles Leclerc", "SAUBER FERRARI", Duration.parse("PT1M12.829S")),
            new Racer("SPF", "Sergio Perez", "FORCE INDIA MERCEDES", Duration.parse("PT1M12.848S")),
            new Racer("RGH", "Romain Grosjean", "HAAS FERRARI", Duration.parse("PT1M12.930S")),
            new Racer("PGS", "Pierre Gasly", "SCUDERIA TORO ROSSO HONDA", Duration.parse("PT1M12.941S")),
            new Racer("CSR", "Carlos Sainz", "RENAULT", Duration.parse("PT1M12.950S")),
            new Racer("EOF", "Esteban Ocon", "FORCE INDIA MERCEDES", Duration.parse("PT1M13.028S")),
            new Racer("NHR", "Nico Hulkenberg", "RENAULT", Duration.parse("PT1M13.065S")),
            new Racer("BHS", "Brendon Hartley", "SCUDERIA TORO ROSSO HONDA", Duration.parse("PT1M13.179S")),
            new Racer("MES", "Marcus Ericsson", "SAUBER FERRARI", Duration.parse("PT1M13.265S")),
            new Racer("LSW", "Lance Stroll", "WILLIAMS MERCEDES", Duration.parse("PT1M13.323S")),
            new Racer("KMH", "Kevin Magnussen", "HAAS FERRARI", Duration.parse("PT1M13.393S"))
        );
        String result = formatter.format(racers);
        
        assertEquals(expected.toString(), result);
    }
}