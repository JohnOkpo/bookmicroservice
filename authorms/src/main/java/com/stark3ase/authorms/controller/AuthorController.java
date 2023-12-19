package com.stark3ase.authorms.controller;


import com.stark3ase.authorms.dto.AuthorRequest;
import com.stark3ase.authorms.service.impl.AuthorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody AuthorRequest authorRequest)
    {
        try{
            AuthorRequest isAuthorCreated = authorService.createNewAuthor(authorRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(isAuthorCreated);
        }catch (Exception ex)
        {
            log.info("error caused by "+ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthor(@PathVariable("id") UUID authorId)
    {
        try{
            AuthorRequest authorDto = authorService.getAuthor(authorId);
            return ResponseEntity.status(HttpStatus.FOUND).body(authorDto);
        }catch (Exception ex)
        {
            log.error("There is an error "+ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAuthor()
    {
        try{
            List<AuthorRequest> listOfAuthors =  authorService.getAllAuthor();
            return ResponseEntity.status(HttpStatus.FOUND).body(listOfAuthors);
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable("id") UUID authorId,  @RequestBody AuthorRequest authorDto)
    {
        try{
            AuthorRequest updateAuthor = authorService.updateAuthor(authorId, authorDto);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(updateAuthor);
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable("id") UUID authorId)
    {
        try{
           Boolean deletedAuthor = authorService.deleteAuthor(authorId);
           return ResponseEntity.status(HttpStatus.NO_CONTENT).body(deletedAuthor);
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable("id") UUID id, @RequestBody AuthorRequest authorRequest)
    {
        try{
            AuthorRequest partialUpdateAuthorRequest = authorService.patchAuthor(id, authorRequest);
            return ResponseEntity.status(HttpStatus.OK).body(partialUpdateAuthorRequest);
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
