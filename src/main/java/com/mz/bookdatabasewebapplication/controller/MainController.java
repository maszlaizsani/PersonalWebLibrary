package com.mz.bookdatabasewebapplication.controller;

import com.mz.bookdatabasewebapplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {

	@Autowired
    BookRepository bookRepository;

    @GetMapping("")
    public String showHomePage(){
        return "index";
    }

}
