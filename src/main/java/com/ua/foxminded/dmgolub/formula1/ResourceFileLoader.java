package com.ua.foxminded.dmgolub.formula1;

import java.util.Optional;

public class ResourceFileLoader extends FileLoader{
    
    public ResourceFileLoader(String fileName) {
        super(Optional.ofNullable(ClassLoader.getSystemResource(fileName))
            .orElseThrow(() -> new IllegalArgumentException("file not found"))
            .getPath());
    }
}