package com.stark3ase.authorms.service.impl;

import com.stark3ase.authorms.dto.AuthorDto;
import com.stark3ase.authorms.dto.AuthorRequest;
import com.stark3ase.authorms.entity.Author;
import com.stark3ase.authorms.entity.AuthorAddress;
import com.stark3ase.authorms.exceptions.AuthorCustomException;
import com.stark3ase.authorms.mapper.AuthorMapper;
import com.stark3ase.authorms.repository.AuthorAddressRepository;
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

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTest {

    @Mock
    AuthorRepository authorRepository;
    @Mock
    AuthorMapper authorMapper;
    @Mock
    AuthorAddressRepository authorAddressRepository;

    AuthorServiceImpl authorService;

    @BeforeEach
    void setUp() {
        authorService =  new AuthorServiceImpl(authorRepository, authorMapper, authorAddressRepository);
    }

    @Test
    void shouldCreateNewAuthor()
    {
        UUID authorId = UUID.fromString("d51b50b0-9286-46b9-b424-4f16fcdbaa75");
        UUID authorAddressId = UUID.fromString("e1150ea1-f50e-4656-9e45-2cceb7d142c6");
        AuthorAddress authorAddress = new AuthorAddress(authorAddressId, "example@gmail.com",
                "09078883332","1234 Ave Street");
        AuthorDto authorDto = new AuthorDto(authorId, "James","John");
        Author author = new Author(authorDto.getAuthorId(), authorDto.getFirstName(), authorDto.getLastName(), authorAddressId);
        AuthorRequest authorRequest = new AuthorRequest(authorDto, authorAddress);

        when(authorRepository.save(author)).thenReturn(author);

        when(authorAddressRepository.save(authorAddress)).thenReturn(authorAddress);
        when(authorMapper.convertToAuthorDto(author)).thenReturn(authorDto);
        when(authorMapper.convertToAuthor(authorDto)).thenReturn(author);

        AuthorRequest createAuthor = authorService.createNewAuthor(authorRequest);

        Mockito.verify(authorRepository, times(1)).save(author);
        Mockito.verify(authorAddressRepository, times(1)).save(authorAddress);

    }

    @Test
    @DisplayName("This is should throw an exception if the passed payloads are empty")
    void shouldThrowExceptionWhenGivenNullPayload()
    {
        AuthorRequest authorRequest = new AuthorRequest(null, null);
        Author author = new Author();

        Assertions.assertThatThrownBy(()->authorService.createNewAuthor(authorRequest))
                        .isInstanceOf(AuthorCustomException.AuthorNotCreatedException.class)
                                .hasMessage("Please provide a valid request");
        Mockito.verify(authorRepository, times(0)).save(author);
    }
    @Test
    @DisplayName("This method will return an author which ID matches the ID in the payload")
    void shouldGetAuthorWithGivenUUID()
    {
        String authorIdString = "9d7a8a85-a84c-4504-bcb9-78876b9ad6e8";
        UUID authorAddressIdString = UUID.fromString("e1150ea1-f50e-4656-9e45-2cceb7d142c6");
        UUID authorId = UUID.fromString(authorIdString);
        Author authorModelReference = new Author(authorId, "John","Jnr", authorAddressIdString);
        AuthorDto authorDto = new AuthorDto(authorModelReference.getAuthorId(),
                authorModelReference.getFirstName(), authorModelReference.getLastName());
        AuthorAddress authorAddress = new AuthorAddress(authorAddressIdString,
                "example@gmail.com",
                "0902345678",
                "123 London Street");

        when(authorRepository.getAuthorByAuthorId(authorId)).thenReturn(authorModelReference);
        when(authorAddressRepository.getAuthorAddressByAddressId(authorAddressIdString)).thenReturn(authorAddress);
        when(authorMapper.convertToAuthorDto(authorModelReference)).thenReturn(authorDto);

        AuthorRequest actual = authorService.getAuthor(authorId);

        Mockito.verify(authorRepository, times(1)).getAuthorByAuthorId(authorId);
        Mockito.verify(authorAddressRepository, times(1))
                .getAuthorAddressByAddressId(authorAddressIdString);

    }

    @Test
    @DisplayName("This test ensures that a run time exception is thrown if the authorId is null")
    void shouldThrowRunTimeExceptionIfAuthorIdIsNull()
    {
        String authorIdString = "8e779cef-b0b3-4c1c-8f68-0662ea523a45";
        UUID authorId = UUID.fromString(authorIdString);
        Assertions.assertThatThrownBy(()->authorService.getAuthor(null))
                .isInstanceOf(RuntimeException.class)
                        .hasMessage("Please provide a valid ID");

        Mockito.verify(authorRepository, times(0)).getAuthorByAuthorId(authorId);
    }

    @Test
    @DisplayName("This verify the action that an error is thrown when the provided authorId does not any of the ID in the DB")
    void shouldThrowRuntimeExceptionIfUserDoesNotExist()
    {
        UUID authorId = UUID.fromString("8e779cef-b0b3-4c1c-8f68-0662ea523a45");
        Author authorModel = new Author();
        AuthorRequest authorDtoModel = new AuthorRequest();

        when(authorRepository.getAuthorByAuthorId(any(UUID.class))).thenReturn(null);
        Assertions.assertThatThrownBy(()->authorService.getAuthor(authorId))
                .isInstanceOf(RuntimeException.class)
                        .hasMessage("Author does not exist");
        Mockito.verify(authorRepository, times(1)).getAuthorByAuthorId(authorId);
    }

    @Test
    void getAllAuthor()
    {
        Author author1 = new Author(UUID.randomUUID(), "John","Jnr", UUID.randomUUID());
        AuthorDto authorDto1 = new AuthorDto(author1.getAuthorId(), author1.getFirstName(),author1.getLastName());
        Author author2 = new Author(UUID.randomUUID(), "John","Jnr", UUID.randomUUID());
        List<Author> listOfAuthor = Arrays.asList(author1, author2);
        AuthorAddress authorAddress =  new AuthorAddress(UUID.randomUUID(), "example@gmail.com",
                "909987654",
                "123 Vein");

        when(authorRepository.findAll()).thenReturn(listOfAuthor);
        when(authorAddressRepository.getAuthorAddressByAddressId(any(UUID.class))).thenReturn(authorAddress);
        when(authorMapper.convertToAuthorDto(author1)).thenReturn(authorDto1);

        List<AuthorRequest> authorRequestsList = authorService.getAllAuthor();
        Assertions.assertThat(authorRequestsList.size()).isGreaterThan(0);
    }

    @Test
    void shouldDeleteAuthor()
    {
        String authorIdString = "9d7a8a85-a84c-4504-bcb9-78876b9ad6e8";
        UUID authorAddressIdString = UUID.fromString("e1150ea1-f50e-4656-9e45-2cceb7d142c6");
        UUID authorId = UUID.fromString(authorIdString);
        Author authorModel = new Author(authorId, "John","James",authorAddressIdString);
        AuthorAddress authorAddress = new AuthorAddress(authorAddressIdString,
                "example@gmail.com","090888333","123 Vent");

        when(authorRepository.getAuthorByAuthorId(authorId)).thenReturn(authorModel);

        authorService.deleteAuthor(authorId);
        Mockito.verify(authorRepository,times(1)).delete(authorModel);
        Mockito.verify(authorAddressRepository,times(1)).deleteById(authorAddressIdString);
    }

    @Test
    @DisplayName("This should throw an exception when a null ID is passed as payload")
    void shouldThrowAnExceptionWhenPayloadIsNull()
    {
        UUID authorId = null;
        Assertions.assertThatThrownBy(()->authorService.deleteAuthor(authorId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Please provide a valid ID");
    }

    @Test
    @DisplayName("This test checks to verify that an exception is thrown when ID does not match any user")
    void shouldThrowAnExceptionWhenUserDoesNotExist()
    {
        String authorIdString = "9d7a8a85-a84c-4504-bcb9-78876b9ad6e8";
        UUID authorId = UUID.fromString(authorIdString);
        when(authorRepository.getAuthorByAuthorId(any(UUID.class))).thenReturn(null);
        Assertions.assertThatThrownBy(()->authorService.deleteAuthor(authorId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("user does not exist");
        Mockito.verify(authorRepository, times(0)).delete(new Author());
    }

    @Test
    void updateAuthor()
    {
        String authorIdString = "9d7a8a85-a84c-4504-bcb9-78876b9ad6e8";
        UUID authorId = UUID.fromString(authorIdString);
        UUID authorAddressIdString = UUID.fromString("e1150ea1-f50e-4656-9e45-2cceb7d142c6");
        AuthorDto authorDto = new AuthorDto(authorId, "james","jnr");
        Author author = new Author(authorDto.getAuthorId(),
                authorDto.getFirstName(),
                authorDto.getLastName(),
                authorAddressIdString);
        AuthorAddress authorAddress = new AuthorAddress(authorAddressIdString,
                "example@gmail.com",
                "0903456788",
                "123 Vent");
        AuthorRequest authorRequest =  new AuthorRequest(authorDto, authorAddress);
        when(authorRepository.getAuthorByAuthorId(authorId)).thenReturn(author);
        when(authorAddressRepository.getAuthorAddressByAddressId(authorAddressIdString)).thenReturn(authorAddress);
        AuthorRequest authorRequestUpdates = authorService.updateAuthor(authorId, authorRequest);

        Mockito.verify(authorRepository, times(1)).save(author);
        Mockito.verify(authorAddressRepository, times(1)).save(authorAddress);
    }

    @Test
    @DisplayName("This throws an exception if the ID is null")
    void shouldThrowAnExceptionWhenIDIsNull()
    {
        UUID authorIdString = null;
        Assertions.assertThatThrownBy(()-> authorService.updateAuthor(authorIdString, new AuthorRequest()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Please provide a valid ID");
        Mockito.verify(authorRepository, never()).getAuthorByAuthorId(UUID.randomUUID());
    }

    @Test
    @DisplayName("This test throws an exception when no matching user is found for a given ID")
    void shouldThrowExceptionWhenUserIsNotFound()
    {
        String authorIdString = "9d7a8a85-a84c-4504-bcb9-78876b9ad6e8";
        UUID authorId = UUID.fromString(authorIdString);
        UUID authorAddressIdString = UUID.fromString("e1150ea1-f50e-4656-9e45-2cceb7d142c6");
        Author author = new Author(authorId, "james","jnr", authorAddressIdString);
        when(authorRepository.getAuthorByAuthorId(any(UUID.class))).thenReturn(null);
        Assertions.assertThatThrownBy(()->authorService.updateAuthor(UUID.randomUUID(), new AuthorRequest()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Author does not exist");
        Mockito.verify(authorRepository, never()).getAuthorByAuthorId(UUID.randomUUID());

    }

    @Test
    void patchAAuthor()
    {
        String authorIdString = "9d7a8a85-a84c-4504-bcb9-78876b9ad6e8";
        UUID authorId = UUID.fromString(authorIdString);
        UUID authorAddressIdString = UUID.fromString("e1150ea1-f50e-4656-9e45-2cceb7d142c6");
        Author author = new Author(authorId, "james","jnr", authorAddressIdString);
        AuthorDto authorDto = new AuthorDto(authorId, "james","jnr");
        AuthorAddress authorAddress = new AuthorAddress(authorAddressIdString,
                "jnr@gmail.com",
                "0888888333",
                "12 Ven");
        AuthorRequest authorRequest = new AuthorRequest(authorDto, authorAddress);
        when(authorRepository.getAuthorByAuthorId(authorId)).thenReturn(author);
        when(authorAddressRepository.getAuthorAddressByAddressId(authorAddressIdString)).thenReturn(authorAddress);

        authorService.patchAuthor(authorId, authorRequest);
        Mockito.verify(authorRepository,times(1)).getAuthorByAuthorId(authorId);
        Mockito.verify(authorAddressRepository, times(1)).getAuthorAddressByAddressId(authorAddressIdString);
    }

    @Test
    @DisplayName("This test will throw an exception when an empty ID is provided")
    void shouldThrowARuntimeExceptionWhenIDIsNull()
    {
        Assertions.assertThatThrownBy(()->authorService.patchAuthor(null, new AuthorRequest()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Please provide a valid ID");
        Mockito.verify(authorRepository, never()).getAuthorByAuthorId(UUID.randomUUID());
    }
}