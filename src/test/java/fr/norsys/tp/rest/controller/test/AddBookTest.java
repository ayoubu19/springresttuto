package fr.norsys.tp.rest.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.norsys.tp.rest.TpRestApplication;
import fr.norsys.tp.rest.bean.Book;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={TpRestApplication.class})
public class AddBookTest extends ABookControllerTest {

    @Test
    @DisplayName("should add book successfully")
    public void should_add_user_succes() throws Exception{
        //Given
        Book book= Book.builder()
                .bookId((long) 1).title("book1").category("fiction").author("me").status("available").borrower("ahmed")
                .build();

        //when
        when(bookService.addBook(book)).thenReturn(book);

        //then
        mockMvc.perform(
                post("/api/v1/books")
                        .content(asJsonString(book))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        )
                .andDo(print())
                .andExpect(status().isCreated());

        verify(bookService, times(1)).addBook(book);
        verifyNoMoreInteractions(bookService);

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
