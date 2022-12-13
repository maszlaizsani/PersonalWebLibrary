package com.mz.bookdatabasewebapplication.service;

import com.mz.bookdatabasewebapplication.model.Book;
import com.mz.bookdatabasewebapplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired private BookRepository bookRepository;

    public List<Book> listAllBooks(){
        return (List<Book>) bookRepository.findAll();
    }

    public void save(Book book){
        bookRepository.save(book);
    }

    public Book getBook(Long id) throws BookNotFoundException {
        Optional<Book> book= bookRepository.findById(id);
        if (book.isPresent()){
            return book.get();
        }
        else throw new BookNotFoundException("Could not find a book with this ID.");
    }

    public void deleteBook(Long id) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()){
            bookRepository.deleteById(id);
        }
        else throw new BookNotFoundException("Could not find a book with this ID.");
    }
}
