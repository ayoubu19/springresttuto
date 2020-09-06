package fr.norsys.tp.rest.service;


import fr.norsys.tp.rest.bean.Book;
import fr.norsys.tp.rest.exception.BookNotFoundException;
import fr.norsys.tp.rest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository ;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByBookId(Long bookId) throws BookNotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Book book = bookOptional.orElseThrow(BookNotFoundException::new);
        return book;

    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
