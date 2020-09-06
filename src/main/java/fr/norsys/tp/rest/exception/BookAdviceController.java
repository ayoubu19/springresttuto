package fr.norsys.tp.rest.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class BookAdviceController {
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(BookNotFoundException.class)
    public void handleNotFound(BookNotFoundException ex) {
        log.error("Requested book is not found");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler(Exception.class)
    public void handleGeneralError(Exception ex) {
        log.error("An error occurred processing request" + ex);
    }
}
