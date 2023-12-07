package com.stark3ase.authorms.mapper;

import com.stark3ase.authorms.config.MapperConfig;
import com.stark3ase.authorms.dto.AuthorDto;
import com.stark3ase.authorms.entity.Author;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper
{
    private final ModelMapper modelMapper;

    public AuthorMapper(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }

    public Author convertToAuthor(AuthorDto authorDto)
    {
        return modelMapper.map(authorDto, Author.class);
    }

    public AuthorDto convertToAuthorDto(Author author)
    {
        return modelMapper.map(author, AuthorDto.class);
    }

}
