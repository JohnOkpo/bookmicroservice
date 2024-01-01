package com.strack3are.bookms.service;

import com.strack3are.bookms.dto.BookModelDto;
import com.strack3are.bookms.dto.RateRequest;
import java.util.List;

public interface BookService
{
    BookModelDto createBook(BookModelDto bookModelDto);
    BookModelDto getBook(Long bookId);
    List<BookModelDto> getAllBooks();
    boolean deleteBook(Long bookId);
    boolean updateBook(Long bookId, BookModelDto bookModelDto);
    void updateRating(RateRequest rateRequest);
}
