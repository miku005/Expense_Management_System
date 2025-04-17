package com.Expense_Management_System;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class A {
    public static void main(String[] args) {
        String encodePWD = BCrypt.hashpw("testing", BCrypt.gensalt(10));
        System.out.println(encodePWD);
    }
}
