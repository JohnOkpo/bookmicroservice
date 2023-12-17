package com.stark3ase.authorms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stark3ase.authorms.dto.AuthorDto;
import com.stark3ase.authorms.dto.AuthorRequest;
import com.stark3ase.authorms.entity.Author;
import com.stark3ase.authorms.entity.AuthorAddress;
import com.stark3ase.authorms.service.impl.AuthorServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthorController.class)
class AuthorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
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
                "",
                "",
                "");
//        AuthorRequest authorDto = new AuthorRequest(authorId, "John ","Jnr", UUID.randomUUID());
        AuthorRequest authorDto = new AuthorRequest(new AuthorDto(), new AuthorAddress());
//        Author authorModel = new Author(authorDto.getAuthorId(), authorDto.getFirstName(), authorDto.getLastName(), authorAddress);
        Author authorModel = new Author();
        mockMvc.perform(post("/getAuthor/"+authorId)
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(new ObjectMapper().writeValueAsString(authorModel)))
                .andExpect(status().isFound());
    }
}