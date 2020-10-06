package com.codecool.fas.model.http;

public class ResponseError<T> extends Response<T> {

    private static final String STATUS_VALUE = "error";

    public ResponseError(String errorMessage) {
        super(STATUS_VALUE, null, errorMessage);
    }

}
