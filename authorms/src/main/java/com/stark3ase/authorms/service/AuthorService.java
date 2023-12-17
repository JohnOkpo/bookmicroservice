package com.stark3ase.authorms.service;

import com.stark3ase.authorms.dto.AuthorRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface AuthorService
{
    AuthorRequest createNewAuthor(AuthorRequest authorDto);
    AuthorRequest getAuthor(UUID authorId);
    List<AuthorRequest> getAllAuthor();
    Boolean deleteAuthor(UUID authorId);
    AuthorRequest updateAuthor(UUID authorId, AuthorRequest authorDto);
    AuthorRequest patchAuthor(UUID authorId, AuthorRequest authorRequest);
}
