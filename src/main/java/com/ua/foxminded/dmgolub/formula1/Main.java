package com.ua.foxminded.dmgolub.formula1;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Main {
    
    private static final String ABB_AND_TIME_REG_EXP = "\\D{3}\\d{4}(-\\d{2}){2}_(\\d{2}:){2}\\d{2}.\\d{1,3}";
    
    public static void main(String[] args) {
        
        Parser<Pair<String, LocalDateTime>> timeMapEntryParser = new TimeMapEntryParser(ABB_AND_TIME_REG_EXP);
        TimeMapBuilder timeMapBuilder = new TimeMapBuilder(timeMapEntryParser);
        
        ResourceFileLoader loader = new ResourceFileLoader("start.log");
        List<String> startLog = loader.load();
        Map<String, LocalDateTime> startTimeMap = timeMapBuilder.build(startLog);

        loader = new ResourceFileLoader("end.log");
        List<String> endLog = loader.load();
        Map<String, LocalDateTime> endTimeMap = timeMapBuilder.build(endLog);

        loader = new ResourceFileLoader("abbreviations.txt");
        List<String> descriptions = loader.load();

        RacerListBuilder racerListBuilder = new RacerListBuilder();
        List<Racer> racers = racerListBuilder.build(descriptions, startTimeMap, endTimeMap);

        FormatterDecorator<List<Racer>> formatter = new QualificationResultFormatter(new StageResultFormatter(), 15);
        System.out.println(formatter.format(racers));
    }
}