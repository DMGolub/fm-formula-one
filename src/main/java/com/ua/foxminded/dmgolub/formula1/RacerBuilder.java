package com.ua.foxminded.dmgolub.formula1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.regex.Pattern;

public class RacerBuilder {
    
    private static final String REG_EXP = "\\D{3}_\\D+\\s\\D+_\\D+";
    
    private Map<String, LocalDateTime> startTimeMap;
    private Map<String, LocalDateTime> endTimeMap;
    
    public RacerBuilder(Map<String, LocalDateTime> startTimeMap, Map<String, LocalDateTime> endTimeMap) {
        if (startTimeMap == null) {
            throw new IllegalArgumentException("startTimeMap can not be null");
        }
        if (endTimeMap == null) {
            throw new IllegalArgumentException("endTimeMap can not be null");
        }
        
        this.startTimeMap = startTimeMap;
        this.endTimeMap = endTimeMap;
    }
    
    public Racer build(String abbreviationNameTeam) {
        if (abbreviationNameTeam == null) {
            throw new IllegalArgumentException("abbreviationNameTeam string can not be null!");
        }
        if (!Pattern.matches(REG_EXP, abbreviationNameTeam)) {
            throw new IllegalArgumentException("Wrong input format: " + abbreviationNameTeam);
        }
        
        String[] racerData = abbreviationNameTeam.split("_");
        String abbreviation = racerData[0];
        String name = racerData[1];
        String team = racerData[2];
        
        LocalDateTime startTimeStamp = startTimeMap.get(abbreviation);
        if (startTimeStamp == null) {
            throw new IllegalArgumentException("start timestamp not found for " + abbreviation);
        }
        LocalDateTime endTimeStamp = endTimeMap.get(abbreviation);
        if (endTimeStamp == null) {
            throw new IllegalArgumentException("end timestamp not found for " + abbreviation);
        }
        
        Duration fastestLap = Duration.between(startTimeStamp, endTimeStamp);
        return new Racer(abbreviation, name, team, fastestLap);
    }
}
