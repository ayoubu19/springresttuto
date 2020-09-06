package fr.norsys.tp.rest.controller.test;

import fr.norsys.tp.rest.TpRestApplication;
import fr.norsys.tp.rest.bean.Book;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {TpRestApplication.class})
public class DeleteBookTest extends ABookControllerTest {
    @Test
    @DisplayName("should delete book successfully")
    public void should_delete_book_succes() throws Exception{
        //Given
       Book bookToDelete= Book.builder()
               .bookId((long) 1).title("book1").category("fiction").author("me").status("available").borrower("ahmed")
               .build();

        //when
        doNothing().when(bookService).deleteBook(bookToDelete.getBookId());

        //then
        mockMvc.perform(delete("/api/v1/books/{bookId}", (long)1))
                .andExpect(status().isAccepted());
        verify(bookService, times(1)).deleteBook(bookToDelete.getBookId());
        verifyNoMoreInteractions(bookService);
    }
}
