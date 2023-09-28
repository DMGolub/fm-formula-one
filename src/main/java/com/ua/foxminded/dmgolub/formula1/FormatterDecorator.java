package com.ua.foxminded.dmgolub.formula1;

public abstract class FormatterDecorator<T> implements Formatter<T> {
    
    private Formatter<T> wrappee;
    
    FormatterDecorator(Formatter<T> wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public String format(T input) {
        return wrappee.format(input);
    }
}