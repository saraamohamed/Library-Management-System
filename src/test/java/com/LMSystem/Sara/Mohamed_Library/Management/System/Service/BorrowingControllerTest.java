package com.LMSystem.Sara.Mohamed_Library.Management.System.Service;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.LMSystem.Sara.Mohamed_Library.Management.System.Controllers.BorrowingController;
import com.LMSystem.Sara.Mohamed_Library.Management.System.Services.BorrowingService;

@WebMvcTest(BorrowingController.class)
public class BorrowingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowingService borrowingService;

    private Long bookId;
    private Long patronId;

    @BeforeEach
    public void setUp() {
        bookId = 1L;
        patronId = 1L;
    }

    @Test
    public void testBorrowBook() throws Exception {
        Mockito.doNothing().when(borrowingService).borrowBook(bookId, patronId);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow/{bookId}/patron/{patronId}", bookId, patronId))
                .andExpect(status().isOk());

        Mockito.verify(borrowingService, Mockito.times(1)).borrowBook(bookId, patronId);
    }

    @Test
    public void testReturnBook() throws Exception {
        Mockito.doNothing().when(borrowingService).returnBook(bookId, patronId);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/return/{bookId}/patron/{patronId}", bookId, patronId))
                .andExpect(status().isOk());

        Mockito.verify(borrowingService, Mockito.times(1)).returnBook(bookId, patronId);
    }
}
