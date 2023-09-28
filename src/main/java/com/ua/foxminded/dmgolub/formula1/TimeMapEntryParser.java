package com.ua.foxminded.dmgolub.formula1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class TimeMapEntryParser implements Parser<Pair<String, LocalDateTime>> {
    
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd_HH:mm:ss.SSS";
    private static final int ABBREVIATION_LENGHTH = 3;
    
    private String abbreviationAndTimeRegExp;
    
    public TimeMapEntryParser(String abbreviationAndTimeRegExp) {
        if (abbreviationAndTimeRegExp == null) {
            throw new IllegalArgumentException("time regular expression can not be null!");
        }
        this.abbreviationAndTimeRegExp = abbreviationAndTimeRegExp;
    }

    @Override
    public Pair<String, LocalDateTime> parse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("input string can not be null!");
        }
        if (!Pattern.matches(abbreviationAndTimeRegExp, input)) {
            throw new IllegalArgumentException("Wrong input string format: " + input);
        }
        String abbreviation = input.substring(0, ABBREVIATION_LENGHTH);
        
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        LocalDateTime dateTime = LocalDateTime.parse(input.substring(3), dateTimeFormatter);
        return new Pair<>(abbreviation, dateTime);
    }
}