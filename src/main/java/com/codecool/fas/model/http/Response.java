package com.codecool.fas.model.http;

import org.springframework.stereotype.Component;

import java.util.List;

public class Response<T> {

    private String status;
    private List<T> values;
    private String errorMessage;

    public Response() {
    }

    public Response(String status, List<T> values, String errorMessage) {
        this.status = status;
        this.values = values;
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
