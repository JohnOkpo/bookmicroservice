package com.stark3ase.authorms.controller;

import com.stark3ase.authorms.dto.AuthorDto;
import com.stark3ase.authorms.service.impl.AuthorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("api/v1/author")
public class AuthorController
{
    private final AuthorServiceImpl authorService;


    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/addAuthor")
    public ResponseEntity<?> createAuthor(@RequestBody AuthorDto authorDto)
    {
        try{
            authorDto.setAuthorId(UUID.randomUUID());
            AuthorDto isAuthorCreated = authorService.createNewAuthor(authorDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(isAuthorCreated);
        }catch (Exception ex)
        {
            log.info("error caused by "+ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

        }
    }



}