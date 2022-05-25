package com.example.wineshop;

public class WineryNotFoundException extends RuntimeException{

    WineryNotFoundException(Long id){
        super("Could not find winery " + id);
    }
}