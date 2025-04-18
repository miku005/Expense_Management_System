package com.Expense_Management_System.Exception;

    public class ResourceNotFound extends RuntimeException{
        public ResourceNotFound(String Message) {
        super(Message);
    }
}
