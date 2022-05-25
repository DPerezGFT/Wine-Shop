package com.example.wineshop;

public class WineNotFoundException extends RuntimeException{

    WineNotFoundException(Long id){
        super("Could not find wine " + id);
    }
}