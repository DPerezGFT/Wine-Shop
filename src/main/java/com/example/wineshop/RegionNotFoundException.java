package com.example.wineshop;

public class RegionNotFoundException extends RuntimeException{

    RegionNotFoundException(Long id){
        super("Could not find region " + id);
    }
}