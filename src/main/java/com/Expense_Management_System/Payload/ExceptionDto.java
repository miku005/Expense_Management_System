package com.Expense_Management_System.Payload;

import java.util.Date;

public class ExceptionDto {

    private Date date;
    private String message;
    private String request;

    public ExceptionDto(Date date, String message, String request) {
        this.date = date;
        this.message = message;
        this.request = request;
    }
}
