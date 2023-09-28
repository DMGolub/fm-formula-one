package com.ua.foxminded.dmgolub.formula1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PairTest {
    
    private Pair<Integer, String> pair = new Pair<>(5, "some string");
    
    @Test
    void getKey_shouldReturnKey_whenKeyIsNotNull() {
        assertEquals(5, pair.getKey());
    }
    
    @Test
    void getValue_shouldReturnValue_whenValueIsNotNull() {
        assertEquals("some string", pair.getValue());
    }
}