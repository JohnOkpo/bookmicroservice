package com.stark3ase.authorms.repository;

import com.stark3ase.authorms.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID>
{
    Author getAuthorByAuthorId(UUID authorId);
}
