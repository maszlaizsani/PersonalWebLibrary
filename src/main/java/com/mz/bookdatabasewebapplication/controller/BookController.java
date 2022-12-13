package com.mz.bookdatabasewebapplication.controller;

import com.mz.bookdatabasewebapplication.model.Book;
import com.mz.bookdatabasewebapplication.service.BookNotFoundException;
import com.mz.bookdatabasewebapplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookService service = new BookService();

    @GetMapping("/books")
    public String getAllBooks(Model model)  {
        List<Book> books;
        books = service.listAllBooks();
        model.addAttribute("books", books);

        return "books";

    }

    @GetMapping("/books/new")
    public String showForm(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("pageTitle", "Add new book");
        return "book_form";
    }

    @PostMapping("/books/save")
    public String saveBook(Book book, RedirectAttributes redirectAttributes){
        service.save(book);
        redirectAttributes.addFlashAttribute("message", "Book has been successfully saved.");
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Book book = service.getBook(id);
            model.addAttribute("book", book);
            model.addAttribute("pageTitle", "Edit book properties (ID:"+ id + ")");
            return "book_form";
        }
        catch (BookNotFoundException e){
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("message", "The book has been saved.");
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        try {
            service.deleteBook(id);
            redirectAttributes.addFlashAttribute("message", "The book has been deleted.");
        }
        catch (BookNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/books";
    }

}
