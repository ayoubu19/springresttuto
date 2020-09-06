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

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {TpRestApplication.class})
public class UpdateBookTest extends ABookControllerTest {
    @Test
    @DisplayName("should update book  successfully")
    public void should_update_book_succes() throws Exception {
        //Given
        Book updatedUser= Book.builder()
                .bookId((long) 1).title("book1").category("fiction").author("me").status("available").borrower("ahmed")
                .build();

        //when
        when(bookService.updateBook(updatedUser)).thenReturn(updatedUser);

        //then
        mockMvc.perform(
                put("/api/v1/books")
                        .content(asJsonString(updatedUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                  )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("book1")))
                .andExpect(jsonPath("$.category", is("fiction")));

        verify(bookService, times(1)).updateBook(updatedUser);
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
