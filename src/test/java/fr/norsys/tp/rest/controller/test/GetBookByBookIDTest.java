package fr.norsys.tp.rest.controller.test;


import fr.norsys.tp.rest.TpRestApplication;
import fr.norsys.tp.rest.bean.Book;
import fr.norsys.tp.rest.exception.BookNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={TpRestApplication.class})
public class GetBookByBookIDTest extends ABookControllerTest {
     @Test
    @DisplayName("should get a book by his id successfully")
    public void should_get_book_by_bookId_succes() throws Exception, BookNotFoundException {
        //Given
         Book book= Book.builder()
                 .bookId((long) 1).title("book1").category("fiction").author("me").status("available").borrower("ahmed")
                 .build();

        //when
        when(bookService.getBookByBookId((long) 1)).thenReturn(book);

        //then
        mockMvc.perform(get("/api/v1/books/{bookId}",(long)1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.title", is("book1")));
        verify(bookService, times(1)).getBookByBookId((long)1);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    @DisplayName("should return book not found when such book doesn t exist")
    public void should_get_book_by_bookId_fail() throws Exception, BookNotFoundException {
        //Given


        //when
        when(bookService.getBookByBookId((long) 1)).thenThrow(new BookNotFoundException());

        //then
        mockMvc.perform(get("/api/v1/books/{bookId}",(long)100)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(bookService, times(1)).getBookByBookId((long) 100);
        verifyNoMoreInteractions(bookService);

    }

}
