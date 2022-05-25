package com.example.wineshop;

public class TypeNotFoundException extends RuntimeException{

    TypeNotFoundException(Long id){
        super("Could not find type " + id);
    }
}