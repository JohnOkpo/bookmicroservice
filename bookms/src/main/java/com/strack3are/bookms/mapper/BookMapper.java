package com.strack3are.bookms.mapper;

import com.strack3are.bookms.dto.BookModelDto;
import com.strack3are.bookms.model.BookModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookMapper
{
    @Autowired
    private ModelMapper modelMapper;

    public BookModel convertToBookModel(BookModelDto bookModelDto)
    {
        return modelMapper.map(bookModelDto, BookModel.class);
    }

    public BookModelDto bookModelOptionalToBook(Optional<BookModel> optionalBookModel)
    {
        return  optionalBookModel.map(this::convertToBookModelDto).orElse(null);
    }

    public BookModelDto convertToBookModelDto(BookModel bookModel)
    {
        return modelMapper.map(bookModel, BookModelDto.class);
    }

}
