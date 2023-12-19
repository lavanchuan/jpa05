package com.chuan.jpa05.exceptions;

public class MeterialNotFoundException extends RuntimeException{
    public MeterialNotFoundException(int id){
        super("Cound not found meterial: " + id);
    }
}
