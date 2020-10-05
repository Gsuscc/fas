package com.codecool.fas.model.http;

import java.util.List;

public class ResponseData<T> extends Response<T> {

    private static final String STATUS_VALUE = "Success";

    public ResponseData(List<T> values) {
        super(STATUS_VALUE, values, null);
    }

}
