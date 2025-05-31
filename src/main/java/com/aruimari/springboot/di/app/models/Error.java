package com.aruimari.springboot.di.app.models;

import java.util.Date;

public class Error {

    private int httpStatus;
    private Date date;
    private String message;
    private String errorType;

    public Error() {

    }

    public Error(int httpStatus, Date date, String message, String errorType) {
        this.httpStatus = httpStatus;
        this.date = date;
        this.message = message;
        this.errorType = errorType;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getErrorType() {
        return errorType;
    }
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

}
