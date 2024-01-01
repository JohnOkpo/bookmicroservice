package com.strack3are.bookms.repository;

import com.strack3are.bookms.model.BookModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Should save new users record when all conditions are met")
    public void shouldSaveUser()
    {
        BookModel book = new BookModel(null, "rdss", UUID.fromString("d51b50b0-9286-46b9-b424-4f16fcdbaa75"), 3L,2000,123L,1 );
        BookModel savedBook = bookRepository.save(book);

        assertThat(savedBook).usingRecursiveComparison().ignoringFields("id").isEqualTo(book);
    }
}