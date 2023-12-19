package com.stark3ase.authorms.repository;

import com.stark3ase.authorms.dto.AuthorDto;
import com.stark3ase.authorms.dto.AuthorRequest;
import com.stark3ase.authorms.entity.Author;
import com.stark3ase.authorms.entity.AuthorAddress;
import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAuthorByAuthorId()
    {
        String authorIdString = "9d7a8a85-a84c-4504-bcb9-78876b9ad6e8";
        UUID authorId = UUID.fromString(authorIdString);
        UUID authorAddressId = UUID.fromString("d51b50b0-9286-46b9-b424-4f16fcdbaa75");

        Author authorForSave = new Author(null, "John ","Jnr", authorAddressId);
        Author saveAuthor = authorRepository.save(authorForSave);

        AuthorAddress authorAddress = new AuthorAddress(
                UUID.randomUUID(),
                "example@gmail.com",
                "90888809",
                "123 vent");

        Author author = authorRepository.getAuthorByAuthorId(saveAuthor.getAuthorId());
        Assertions.assertThat(author).isEqualTo(new Author(saveAuthor.getAuthorId(), "John ","Jnr", authorAddressId));
    }
}