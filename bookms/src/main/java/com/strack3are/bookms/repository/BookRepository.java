package com.strack3are.bookms.repository;

import com.strack3are.bookms.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, Long> {
}
