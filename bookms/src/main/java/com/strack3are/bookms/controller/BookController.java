package com.strack3are.bookms.controller;

import com.strack3are.bookms.dto.BookModelDto;
import com.strack3are.bookms.service.impl.BookServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/book")
@AllArgsConstructor
public class BookController
{
    @Autowired
    private BookServiceImpl bookService;

    @PostMapping("/addBook")
    public ResponseEntity<BookModelDto> addBook(@RequestBody BookModelDto bookModelDto)
    {
        try{
            BookModelDto isBookAdded = bookService.createBook(bookModelDto);
            return new ResponseEntity<BookModelDto>(isBookAdded,HttpStatus.CREATED);
        }catch(Exception ex)
        {
            log.error(ex.getMessage());
            return new ResponseEntity<BookModelDto>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBook/{bookId}")
    public ResponseEntity<BookModelDto> getBook(@PathVariable("bookId") Long bookId)
    {
        try{
            BookModelDto isBookPresent = bookService.getBook(bookId);
            return new ResponseEntity<BookModelDto>(isBookPresent,HttpStatus.FOUND);
        }catch (Exception ex)
        {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBooks")
    public ResponseEntity<List<BookModelDto>> getAllBook()
    {
        try{
            List<BookModelDto> listOfAllBooks = bookService.getAllBooks();
//            return new ResponseEntity<List<BookModelDto>>(listOfAllBooks,HttpStatus.FOUND);
            return ResponseEntity.ok().body(listOfAllBooks);
        }catch (Exception ex)
        {
            log.error(ex.getMessage());
            return new ResponseEntity<List<BookModelDto>>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/updateBook/{bookId}")
    public ResponseEntity<?> updateBookRecord(@PathVariable("bookId") Long bookId,@RequestBody BookModelDto bookModelDto)
    {
        try{
            boolean isBookUpdated = bookService.updateBook(bookId, bookModelDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex)
        {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<?> deleteBookRecord(@PathVariable("bookId") Long bookId)
    {
        try
        {
            boolean isDeleted = bookService.deleteBook(bookId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex)
        {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
