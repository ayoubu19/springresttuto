package fr.norsys.tp.rest.exception;

public class BookNotFoundException extends Throwable {
    public BookNotFoundException(){
        super(" requested book is not found !!!");
    }
}
