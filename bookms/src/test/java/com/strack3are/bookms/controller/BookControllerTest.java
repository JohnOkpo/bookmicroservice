package com.strack3are.bookms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strack3are.bookms.clients.AuthorClient;
import com.strack3are.bookms.dto.BookModelDto;
import com.strack3are.bookms.service.impl.BookServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {

    @MockBean
    private BookServiceImpl bookService;
    @MockBean
    private AuthorClient authorClient;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should save a new book to database when a request is made to endpoint /book/addBook")
    public void shouldAddNewBook() throws Exception {
        BookModelDto actualBookModel = new BookModelDto(null,
                "book_title1", UUID.fromString("d51b50b0-9286-46b9-b424-4f16fcdbaa75"), 23L, 24,3L, 1);

        BookModelDto expectedBookModel = new BookModelDto(1L, "book_title2",
                UUID.fromString("d51b50b0-9286-46b9-b424-4f16fcdbaa75"),34L,23, 34L, 1);

        Mockito.when(bookService.createBook(actualBookModel)).thenReturn(expectedBookModel);
        mockMvc.perform(post("/book/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonObject(expectedBookModel))
        )
        .andExpect(status().isCreated());




    }

    @Test
    @DisplayName("Should get a single book record when the endpoint is called - /book/getBook")
    public void shouldGetSingleBook() throws Exception {


        BookModelDto actualBookModel = new BookModelDto(null,
                "book_title1", UUID.fromString("d51b50b0-9286-46b9-b424-4f16fcdbaa75"), 23L,
                24,3L,1);

        BookModelDto expectedBookModel = new BookModelDto(1L, "book_title2",
                UUID.fromString("d51b50b0-9286-46b9-b424-4f16fcdbaa75"),34L,23,
                34L,1);
        //Create Post
        Mockito.when(bookService.createBook(actualBookModel)).thenReturn(expectedBookModel);
        mockMvc.perform(post("/book/addBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonObject(actualBookModel))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("book_title2"))
        ;

        //Checking if created
        Mockito.when(bookService.getBook(1L)).thenReturn(expectedBookModel);
        mockMvc.perform(get("/book/getBook/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isFound())
//                .andExpect(jsonPath("$.id").value(1))
        ;
    }

    @Test
    @DisplayName("Should List All Books When Making a GET request to endpoint /book/getAllBooks/")
    void shouldListAllBook() throws Exception
    {
        BookModelDto bookModelDto1 = new BookModelDto(
          1L, "testBook1",UUID.fromString("d51b50b0-9286-46b9-b424-4f16fcdbaa75"),3L,
                23, 235L,1
        );
        BookModelDto bookModelDto2 = new BookModelDto(
                2L, "testBook2", UUID.fromString("b51b50b0-9286-46b9-d424-6f16fcdbaa74"),
                12L, 45, 890L,1
        );

        Mockito.when(bookService.getAllBooks()).thenReturn(Arrays.asList(bookModelDto1, bookModelDto2));
        mockMvc.perform(MockMvcRequestBuilders.get("/book/getBooks"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", is(2)));
//                .andExpect(MockMvcResultMatchers.jsonPath())

    }

    @Test
    void updateBookRecord() throws Exception {
        BookModelDto actualBookModel = new BookModelDto(
                null, "book_model",UUID.fromString("d51b50b0-9286-46b9-b424-4f16fcdbaa75"),22L,34,44L, 1
        );

        BookModelDto expectedBookModel = new BookModelDto(
                1L, "book_model",UUID.fromString("d51b50b0-9286-46b9-b424-4f16fcdbaa75"),22L,34,44L,1
        );

        //Create
        Mockito.when(bookService.createBook(actualBookModel)).thenReturn(expectedBookModel);
        mockMvc.perform(post("/book/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonObject(actualBookModel)))
                .andExpect(status().isCreated());

        //Updated
        BookModelDto bookModelUpdate = new BookModelDto(1L, "book_model_1", UUID.fromString("d51b50b0-9286-46b9-b424-4f16fcdbaa75"), 22L,
                34, 22L, 1);
        Mockito.when(bookService.updateBook(1L, bookModelUpdate)).thenReturn(true);
        mockMvc.perform(put("/book/updateBook/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonObject(bookModelUpdate))
        )
                .andExpect(status().isNoContent())
//                .andExpect(jsonPath("$.title").value("book_model_1"))
        ;
    }

    @Test
    public void deleteBookRecord() throws Exception {
        BookModelDto actualBookModel = new BookModelDto(
                null, "book_model",UUID.fromString("d51b50b0-9286-46b9-b424-4f16fcdbaa75"),22L,34,44L,1
        );

        BookModelDto expectedBookModel = new BookModelDto(
                1L, "book_model",UUID.fromString("d51b50b0-9286-46b9-b424-4f16fcdbaa75"),22L,34,44L,1
        );

        //Create
        Mockito.when(bookService.deleteBook(1L)).thenReturn(true);
        mockMvc.perform(post("/book/bookDelete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonObject(actualBookModel)));
    }

    public static String asJsonObject(final Object obj)
    {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

}