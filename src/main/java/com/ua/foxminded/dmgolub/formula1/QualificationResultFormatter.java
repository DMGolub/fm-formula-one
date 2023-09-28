package com.ua.foxminded.dmgolub.formula1;

import java.util.Arrays;
import java.util.List;

public class QualificationResultFormatter extends FormatterDecorator<List<Racer>> {
    
    private int delimiterPosition;

    QualificationResultFormatter(Formatter<List<Racer>> wrappee, int delimiterPosition) {
        super(wrappee);
        if (delimiterPosition <= 0) {
            throw new IllegalArgumentException("underline position must be a positive number!");
        }
        this.delimiterPosition = delimiterPosition;
    }
    
    @Override
    public String format(List<Racer> input) {
        if (delimiterPosition < input.size()) {        
            String formattedList = super.format(input);
            int lineLength = formattedList.indexOf(System.lineSeparator());
            int delimiterIndex = (lineLength + System.lineSeparator().length()) * delimiterPosition;
            StringBuilder result = new StringBuilder(formattedList);
            result.insert(delimiterIndex, getDelimiter('-', lineLength));
            return result.toString();
        } else {
            return super.format(input);
        }
    }
    
    private String getDelimiter(char character, int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, character);
        return new String(chars) + System.lineSeparator();
    }
}