package fr.norsys.tp.rest.controller;

import fr.norsys.tp.rest.bean.Book;
import fr.norsys.tp.rest.exception.BookNotFoundException;
import fr.norsys.tp.rest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin(origins = "*")
public class BookController {
    private BookService bookService ;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getUserByUserNo(@PathVariable("bookId") Long bookId) throws BookNotFoundException {
      Book book = bookService.getBookByBookId(bookId);
        if (book!= null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book addedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedBook);
    }


    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        Book updatedBook = bookService.updateBook(book);
        return ResponseEntity.ok().body(updatedBook);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<HttpStatus> removeBook(@PathVariable("bookId") Long bookId) {
      bookService.deleteBook(bookId);
      return ResponseEntity.accepted().build();

    }


}
