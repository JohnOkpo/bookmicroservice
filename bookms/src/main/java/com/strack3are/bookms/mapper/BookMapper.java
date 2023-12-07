package com.strack3are.bookms.mapper;

import com.strack3are.bookms.dto.BookModelDto;
import com.strack3are.bookms.model.BookModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper
{
    @Autowired
    private ModelMapper modelMapper;

    public BookModel convertToBookModel(BookModelDto bookModelDto)
    {
        return modelMapper.map(bookModelDto, BookModel.class);
    }

    public BookModelDto convertToBookModelDto(BookModel bookModel)
    {
        return modelMapper.map(bookModel, BookModelDto.class);
    }

}
