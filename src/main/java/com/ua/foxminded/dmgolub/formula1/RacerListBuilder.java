package com.ua.foxminded.dmgolub.formula1;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacerListBuilder {
    
    List<Racer> build(List<String> descriptions, Map<String, LocalDateTime> startTimeMap, 
            Map<String, LocalDateTime> endTimeMap) {
        if (descriptions == null) {
            throw new IllegalArgumentException("descriptions list can not be null!");
        }
        if (startTimeMap == null) {
            throw new IllegalArgumentException("startTime map can not be null!");
        }
        if (endTimeMap == null) {
            throw new IllegalArgumentException("endTime map can not be null!");
        }
        
        RacerBuilder racerBuilder = new RacerBuilder(startTimeMap, endTimeMap);
        return descriptions.stream()
            .map(description -> racerBuilder.build(description))
            .sorted(Comparator.comparing(Racer::getFastestLap))
            .collect(Collectors.toList());
    }
}