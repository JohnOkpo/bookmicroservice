package com.strack3are.bookms.repository;

import com.strack3are.bookms.model.BookModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomRepositoryTest
{
//    @Autowired
//    private BookRepository bookRepository;
//    @Container
//    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
//            .withDatabaseName("testdatabase")
//            .withUsername("root")
//            .withPassword("");
//
//    @Test
//    public void shouldSavePost()
//    {
//        BookModel expectedBookObject = new BookModel(null, "First_Book", 1L,234L, 2003,
//                2L);
//        BookModel actualBookObject = bookRepository.save(expectedBookObject);
//        assertThat(actualBookObject).usingRecursiveComparison().ignoringFields("id")
//                .isEqualTo(expectedBookObject);
//    }
}
