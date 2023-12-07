package com.stark3ase.authorms.service;

import com.stark3ase.authorms.dto.AuthorDto;
import com.stark3ase.authorms.entity.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorService
{
    AuthorDto createNewAuthor(AuthorDto authorDto);
    AuthorDto getAuthor(UUID authorId);
    List<AuthorDto> getAllAuthor();
    Boolean deleteAuthor(UUID authorId);
    AuthorDto updateAuthor(UUID authorId, AuthorDto authorDto);
    AuthorDto patchAAuthor(UUID authorId, AuthorDto authorDto);
}
