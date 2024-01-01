package com.strack3are.bookms.service.impl;

import com.strack3are.bookms.dto.BookModelDto;
import com.strack3are.bookms.mapper.BookMapper;
import com.strack3are.bookms.model.BookModel;
import com.strack3are.bookms.repository.BookRepository;
import com.strack3are.bookms.service.BookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    BookServiceImpl bookService;

    @BeforeEach
    public void setUp() {
        bookService = new BookServiceImpl(bookRepository, bookMapper);
    }
//
//    @AfterEach
//    void tearDown() {
//    }

//    private BookRepository bookRepository = Mockito.mock(BookRepository.class);
//    private BookMapper bookMapper = Mockito.mock(BookMapper.class);
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;

    @Captor
    private ArgumentCaptor<BookModelDto> bookModelDtoArgumentCaptor;

    @Test
    void createBook()
    {

    }

    @Test
    @DisplayName("Should find book by ID")
    void shouldFindBookById() {


        BookModel bookModel = new BookModel(1L,
                "sample_title",
                UUID.fromString("d51b50b0-9286-46b9-b424-4f16fcdbaa75"),
                45L,2004, 34L,1);

        BookModelDto expectedBookResponse = new BookModelDto(1L,
                "sample_title",
                UUID.fromString("d51b50b0-9286-46b9-b424-4f16fcdbaa75"),
                45L,2004, 34L,1);

        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(bookModel));
        Mockito.when(bookMapper.convertToBookModelDto(Mockito.any(BookModel.class))).thenReturn(expectedBookResponse);


        BookModelDto actualBookResponse = bookService.getBook(1L);

        Assertions.assertThat(expectedBookResponse.getId()).isEqualTo(actualBookResponse.getId());
        Assertions.assertThat(expectedBookResponse.getTitle()).isEqualTo(actualBookResponse.getTitle());
    }

    @Test
    void getAllBooks()
    {

    }

    @Test
    void deleteBook()
    {

    }

    @Test
    void updateBook()
    {

    }
}