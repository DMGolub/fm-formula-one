package com.ua.foxminded.dmgolub.formula1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class FileLoaderTest {
    
    @Test
    void fileLoader_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new FileLoader(null));
    }
    
    @Test
    void fileLoader_shouldThrowIllegalArgumentException_whenAddressIsAnEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> new FileLoader(""));
    }

    @Test
    void fileLoader_shouldReturnAnEmptyList_whenTheGivenFileIsEmpty() {
        List<String> expected = new ArrayList<>();
    
        String path = ClassLoader.getSystemResource("emptyTestFile.txt").getPath();
        Loader<List<String>> loader = new FileLoader(path);

        assertEquals(expected, loader.load());
    }
    
    @Test
    void fileLoader_shouldReturnAListWithOneString_whenFileContainsOneLine() {
        List<String> expected = new ArrayList<>();
        expected.add("some random string");

        String path = ClassLoader.getSystemResource("oneLineTestFile.txt").getPath();
        Loader<List<String>> loader = new FileLoader(path);
        
        assertEquals(expected, loader.load());
    }
    
    @Test
    void fileLoader_shouldReturnAListWithMultipleElements_whenMultilineFileIsGiven() {
        List<String> expected = new ArrayList<>(Arrays.asList(
            "DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER", 
            "SVF_Sebastian Vettel_FERRARI", 
            "LHM_Lewis Hamilton_MERCEDES", 
            "KRF_Kimi Raikkonen_FERRARI", 
            "VBM_Valtteri Bottas_MERCEDES", 
            "EOF_Esteban Ocon_FORCE INDIA MERCEDES", 
            "FAM_Fernando Alonso_MCLAREN RENAULT", 
            "CSR_Carlos Sainz_RENAULT", 
            "SPF_Sergio Perez_FORCE INDIA MERCEDES", 
            "PGS_Pierre Gasly_SCUDERIA TORO ROSSO HONDA", 
            "NHR_Nico Hulkenberg_RENAULT", 
            "SVM_Stoffel Vandoorne_MCLAREN RENAULT", 
            "SSW_Sergey Sirotkin_WILLIAMS MERCEDES", 
            "CLS_Charles Leclerc_SAUBER FERRARI", 
            "RGH_Romain Grosjean_HAAS FERRARI", 
            "BHS_Brendon Hartley_SCUDERIA TORO ROSSO HONDA", 
            "MES_Marcus Ericsson_SAUBER FERRARI", 
            "LSW_Lance Stroll_WILLIAMS MERCEDES", 
            "KMH_Kevin Magnussen_HAAS FERRARI"
        ));

        String path = ClassLoader.getSystemResource("abbreviations.txt").getPath();
        Loader<List<String>> loader = new FileLoader(path);
        
        assertEquals(expected, loader.load());
    }
}
