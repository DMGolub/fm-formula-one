package com.ua.foxminded.dmgolub.formula1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;

public class StageResultFormatter implements Formatter<List<Racer>> {
    
    private static final String TIME_FORMAT = "m:ss.SSS";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    @Override
    public String format(List<Racer> racers) {
        if (racers == null) {
            throw new IllegalArgumentException("Racers list can not be null!");
        }
        int nameMaxLength = 0;
        int teamMaxLength = 0;
        for (Racer racer : racers) {
            nameMaxLength = Math.max(nameMaxLength, racer.getName().length());
            teamMaxLength = Math.max(teamMaxLength, racer.getTeam().length());
        }
        StringJoiner result = new StringJoiner(System.lineSeparator());
        int index = 1;
        for (Racer racer : racers) {
            StringBuilder row = new StringBuilder();
            row.append(index)
               .append(padRight(".", countDigits(racers.size()) - countDigits(index) + 2))
               .append(padRight(racer.getName(), nameMaxLength))
               .append("|")
               .append(padRight(racer.getTeam(), teamMaxLength))
               .append("|")
               .append(TIME_FORMATTER.format(LocalTime.MIDNIGHT.plus(racer.getFastestLap())));
            result.add(row.toString());
            ++index;
        }
        return result.toString();
    }
    
    private String padRight(String str, int spaces) {
        return String.format("%-" + spaces + "s", str);
    }
    
    private int countDigits(Integer number) {
        return number.toString().length();
    }
}