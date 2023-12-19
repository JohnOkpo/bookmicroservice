package com.stark3ase.authorms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stark3ase.authorms.dto.AuthorDto;
import com.stark3ase.authorms.dto.AuthorRequest;
import com.stark3ase.authorms.entity.Author;
import com.stark3ase.authorms.entity.AuthorAddress;
import com.stark3ase.authorms.service.impl.AuthorServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthorController.class)
class AuthorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthorServiceImpl authorService;

    @Test
    void createAuthor() {
    }

    @Test
    void shouldGetAuthorById() throws Exception {
        String authorIdString = "9d7a8a85-a84c-4504-bcb9-78876b9ad6e8";
        UUID authorId = UUID.fromString(authorIdString);
        AuthorAddress authorAddress = new AuthorAddress(
                UUID.randomUUID(),
                "example@gmail.com",
                "90888809",
                "123 vent");
        AuthorDto authorDto = new AuthorDto(authorId, "John ","Jnr");
        AuthorRequest authorRequest = new AuthorRequest(authorDto, authorAddress);
        when(authorService.getAuthor(authorId)).thenReturn(authorRequest);
        mockMvc.perform(get("/api/v1/author/"+authorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authorRequest)))
                .andExpect(status().isFound());
    }

    @Test
    @DisplayName("This test ensures the controller flags an error when a bad request is sent")
    void shouldThrowAnException() throws Exception {
        UUID authorId = null;
        AuthorRequest authorRequest = new AuthorRequest(null, null);
        when(authorService.getAuthor(null)).thenReturn(authorRequest);
        mockMvc.perform(get("/api/v1/author/"+authorId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("This test to ensure that user is created successfully and returns the correct status code")
    void shouldCreateAnAuthorAndReturn200Ok() throws Exception {
        String authorIdString = "9d7a8a85-a84c-4504-bcb9-78876b9ad6e8";
        UUID authorId = UUID.fromString(authorIdString);
        AuthorAddress authorAddress = new AuthorAddress(
                UUID.randomUUID(),
                "example@gmail.com",
                "90888809",
                "123 vent");
        AuthorDto authorDto = new AuthorDto(authorId, "John ","Jnr");
        AuthorRequest authorRequest = new AuthorRequest(authorDto, authorAddress);
        when(authorService.createNewAuthor(authorRequest)).thenReturn(authorRequest);
        mockMvc.perform(post("/api/v1/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authorRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("This test verify that error is properly handled when a user details does not matche")
    void shouldThrowAnExceptionIfUserWasNotCreated() throws Exception {
        AuthorRequest authorRequest = new AuthorRequest(null, null);
        when(authorService.createNewAuthor(null)).thenReturn(null);
        mockMvc.perform(post("/api/v1/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("This test verify that the update function is working")
    void shouldUpdate() throws Exception {
        String authorIdString = "9d7a8a85-a84c-4504-bcb9-78876b9ad6e8";
        UUID authorId = UUID.fromString(authorIdString);
        AuthorAddress authorAddress = new AuthorAddress(
                UUID.randomUUID(),
                "example@gmail.com",
                "90888809",
                "123 vent");
        AuthorDto authorDto = new AuthorDto(authorId, "John ","Jnr");
        AuthorRequest authorRequest = new AuthorRequest(authorDto, authorAddress);
        when(authorService.updateAuthor(authorId, authorRequest)).thenReturn(authorRequest);
        mockMvc.perform(put("/api/v1/author/"+authorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authorRequest))
                )
                .andExpect(status().isNoContent());
    }


    @Test
    @DisplayName("This test verify that the update function is working")
    void shouldPatch() throws Exception {
        String authorIdString = "9d7a8a85-a84c-4504-bcb9-78876b9ad6e8";
        UUID authorId = UUID.fromString(authorIdString);
        AuthorAddress authorAddress = new AuthorAddress(
                UUID.randomUUID(),
                "example@gmail.com",
                "90888809",
                "123 vent");
        AuthorDto authorDto = new AuthorDto(authorId, "John ","Jnr");
        AuthorRequest authorRequest = new AuthorRequest(authorDto, authorAddress);
        when(authorService.patchAuthor(authorId, authorRequest)).thenReturn(authorRequest);
        mockMvc.perform(patch("/api/v1/author/"+authorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authorRequest))
                )
                .andExpect(status().isOk());
    }
}