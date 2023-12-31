package com.strack3are.bookms.service.impl;

import com.strack3are.bookms.dto.BookModelDto;
import com.strack3are.bookms.dto.RateRequest;
import com.strack3are.bookms.exception.NullFieldException;
import com.strack3are.bookms.mapper.BookMapper;
import com.strack3are.bookms.model.BookModel;
import com.strack3are.bookms.repository.BookRepository;
import com.strack3are.bookms.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookModelDto createBook(BookModelDto bookModelDto)
    {
        if(bookModelDto.getTitle().isEmpty())
        {
            throw new NullFieldException("Please provide the required values, for empty fields");
        }
        BookModel bookModel = bookMapper.convertToBookModel(bookModelDto);
        BookModel savedBook = bookRepository.save(bookModel);

        return bookMapper.convertToBookModelDto(savedBook);
    }

    @Override
    public BookModelDto getBook(Long bookId) {
        Optional<BookModel> isBookPresent = bookRepository.findById(bookId);
        if(isBookPresent.isPresent())
        {
            BookModelDto bookModelDto = bookMapper.convertToBookModelDto(isBookPresent.get());
            return bookModelDto;
        }
        throw new NullFieldException("No records for the specified ID");
    }

    @Override
    public List<BookModelDto> getAllBooks() {
        List<BookModel> bookModelList = bookRepository.findAll();
        List<BookModelDto> bookModelDtos = new ArrayList<>();

        for (BookModel book:
             bookModelList) {
            bookModelDtos.add(bookMapper.convertToBookModelDto(book));
        }

        return bookModelDtos;
    }

    @Override
    public boolean deleteBook(Long bookId) {
        Optional<BookModel> isBookFound = bookRepository.findById(bookId);
        if(isBookFound.isPresent())
        {
            bookRepository.deleteById(bookId);
            return true;
        }
        throw new NullFieldException("No value matching the given ID was found");
    }

    @Override
    public boolean updateBook(Long bookId, BookModelDto bookModelDto) {
        Optional<BookModel> isBookFound = bookRepository.findById(bookId);

        if(isBookFound.isPresent())
        {
            BookModel fetchedBook = isBookFound.get();
            fetchedBook.setId(bookModelDto.getId());
            fetchedBook.setTitle(bookModelDto.getTitle());
            fetchedBook.setAuthorId(bookModelDto.getAuthorId());
            fetchedBook.setIsbn(bookModelDto.getIsbn());
            fetchedBook.setPublisherId(bookModelDto.getPublisherId());
            fetchedBook.setYOfP(bookModelDto.getYOfP());
            bookRepository.save(fetchedBook);
            return true;
        }
        else {
            throw new NullFieldException("No value matching the given ID was found");
        }

    }

    @Override
    public void updateRating(RateRequest rateRequest)
    {
        Optional<BookModel> book = bookRepository.findById(rateRequest.getBookId());;
        BookModel allBookValue = book.get();
        allBookValue.setRating(allBookValue.getRating()+1);
        BookModel updatedBook = new BookModel(allBookValue.getId(), allBookValue.getTitle(), allBookValue.getAuthorId(),
                allBookValue.getPublisherId(), allBookValue.getYOfP(),allBookValue.getIsbn(),
                allBookValue.getRating());
        bookRepository.save(updatedBook);
    }
}
