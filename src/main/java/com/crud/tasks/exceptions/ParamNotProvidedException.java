package com.crud.tasks.exceptions;

public class ParamNotProvidedException extends Exception {
    public ParamNotProvidedException() {
        super("Parameter not provided");
    }
}
