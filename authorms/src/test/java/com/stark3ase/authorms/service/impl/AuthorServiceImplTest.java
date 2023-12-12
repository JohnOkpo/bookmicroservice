package com.stark3ase.authorms.service.impl;

import com.stark3ase.authorms.dto.AuthorDto;
import com.stark3ase.authorms.entity.Author;
import com.stark3ase.authorms.entity.AuthorAddress;
import com.stark3ase.authorms.mapper.AuthorMapper;
import com.stark3ase.authorms.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
@Slf4j
@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTest {

    @Mock
    AuthorRepository authorRepository;
    @Mock
    AuthorMapper authorMapper;

    AuthorServiceImpl authorService;

    @BeforeEach
    void setUp() {
        authorService =  new AuthorServiceImpl(authorRepository, authorMapper);
    }

    @Test
    void createNewAuthor() {
    }

    @Test
    @DisplayName("This method will return an author which ID matches the ID in the payload")
    void shouldGetAuthorWithGivenUUID()
    {
        String authorIdString = "9d7a8a85-a84c-4504-bcb9-78876b9ad6e8";
        UUID authorId = UUID.fromString(authorIdString);
        Author authorModelReference = new Author(authorId, "John","Jnr", new AuthorAddress());
        AuthorAddress authorAddress = new AuthorAddress(UUID.randomUUID(),
                "example@gmail.com",
                "0902345678",
                "123 London Street",
                authorModelReference);
        Author authorModel = new Author(authorId, "John","Jnr", authorAddress);
        AuthorDto authorDtoModel = new AuthorDto(authorId, "John", "Jnr", authorAddress);

        when(authorRepository.getAuthorByAuthorId(authorId)).thenReturn(authorModel);
        when(authorMapper.convertToAuthorDto(authorModel)).thenReturn(authorDtoModel);

        AuthorDto actual = authorService.getAuthor(authorId);
        Assertions.assertThat(actual.getFirstName()).isEqualTo(authorModel.getFirstName());
        Assertions.assertThat(actual.getLastName()).isEqualTo(authorModel.getLastName());
        Assertions.assertThat(actual.getAuthorId()).isEqualTo(authorModel.getAuthorId());

    }

    @Test
    @DisplayName("This test ensures that a run time exception is thrown if the authorId is null")
    void shouldThrowRunTimeExceptionIfAuthorIdIsNull()
    {
        String authorIdString = "8e779cef-b0b3-4c1c-8f68-0662ea523a45";
        UUID authorId = UUID.fromString(authorIdString);

        RuntimeException exception = assertThrows(RuntimeException.class, ()-> authorService.getAuthor(null));
        log.error(exception.getMessage());
        Assertions.assertThat("Please provide a valid ID").isEqualTo(exception.getMessage());
        Mockito.verify(authorRepository, times(0)).getAuthorByAuthorId(authorId);
    }

    @Test
    @DisplayName("This verify the action that an error is thrown when the provided authorId does not any of the ID in the DB")
    void shouldThrowRuntimeExceptionIfUserDoesNotExist()
    {
        UUID authorId = UUID.fromString("8e779cef-b0b3-4c1c-8f68-0662ea523a45");
        Author authorModel = new Author();
        AuthorDto authorDtoModel = new AuthorDto();

        when(authorRepository.getAuthorByAuthorId(any(UUID.class))).thenReturn(null);
        assertThrows(RuntimeException.class, ()->authorService.getAuthor(authorId));
        Mockito.verify(authorRepository, times(1)).getAuthorByAuthorId(authorId);
    }

    @Test
    void getAllAuthor() {
    }

    @Test
    void deleteAuthor() {
    }

    @Test
    void updateAuthor() {
    }

    @Test
    void patchAAuthor() {
    }
}