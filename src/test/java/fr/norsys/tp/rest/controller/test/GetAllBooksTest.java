package fr.norsys.tp.rest.controller.test;


import fr.norsys.tp.rest.TpRestApplication;
import fr.norsys.tp.rest.bean.Book;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={TpRestApplication.class})
public class GetAllBooksTest extends ABookControllerTest{

    @Test
    @DisplayName("should get all books successfully")
    public void should_get_all_books_successfully() throws Exception{
        //Given
        List<Book> books =  new ArrayList<>();
        books.add(Book.builder().bookId((long) 1).title("book1").category("fiction").author("me").status("available").borrower("ahmed").build());
        books.add(Book.builder().bookId((long) 2).title("book2").category("fun").author("ayoub").status("available").borrower("youness").build());
        books.add(Book.builder().bookId((long) 3).title("book3").category("manga").author("odda").status("not-available").borrower("khalid").build());

        //when
        when(bookService.getAllBooks()).thenReturn(books);

        //then
        mockMvc.perform(get("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].title").value("book1"))
                .andExpect(jsonPath("$[1].title").value("book2"))
                .andExpect(jsonPath("$[2].title").value("book3"))
                .andReturn();


        verify(bookService, times(1)).getAllBooks();
        verifyNoMoreInteractions(bookService);
    }



}
