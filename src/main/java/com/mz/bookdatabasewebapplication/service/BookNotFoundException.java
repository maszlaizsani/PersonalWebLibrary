package com.mz.bookdatabasewebapplication.service;

public class BookNotFoundException extends Throwable {

    public BookNotFoundException(String message) {
        super(message);
    }
}
