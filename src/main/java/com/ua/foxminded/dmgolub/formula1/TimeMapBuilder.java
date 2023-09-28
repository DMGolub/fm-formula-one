package com.ua.foxminded.dmgolub.formula1;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TimeMapBuilder {
    
    private Parser<Pair<String, LocalDateTime>> parser;
    
    public TimeMapBuilder(Parser<Pair<String, LocalDateTime>> parser) {
        if (parser == null) {
            throw new IllegalArgumentException("parser can not be null!");
        }
        this.parser = parser;
    }
    
    public Map<String, LocalDateTime> build(List<String> input) {
        if (input == null) {
            throw new IllegalArgumentException("Input list can not be null!");
        }
        
        return input.stream()
            .map(parser::parse)
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }
}