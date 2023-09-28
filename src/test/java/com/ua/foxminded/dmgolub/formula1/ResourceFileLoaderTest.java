package com.ua.foxminded.dmgolub.formula1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class ResourceFileLoaderTest {
    
    @Test
    void constructor_shouldThrowIllegalArgumentException_whenFileNotFound() {
        assertThrows(IllegalArgumentException.class, () -> new ResourceFileLoader("file_not_found.txt"));
    }

    @Test
    void resourceFileLoader_shouldLoadDataFromFile_whenAFileNameWithoutPathIsGiven() {
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
        
        Loader<List<String>> loader = new ResourceFileLoader("abbreviations.txt");
        
        assertEquals(expected, loader.load());
    }
}