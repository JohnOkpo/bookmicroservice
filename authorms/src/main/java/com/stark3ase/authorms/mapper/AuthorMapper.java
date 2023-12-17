package com.stark3ase.authorms.mapper;

import com.stark3ase.authorms.dto.AuthorDto;
import com.stark3ase.authorms.dto.AuthorRequest;
import com.stark3ase.authorms.entity.Author;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<AuthorDto> convertToAuthorDtoList(List<Author> authorList)
    {
        return authorList.stream()
                .map(this::convertToAuthorDto)
                .collect(Collectors.toList());
    }

    public Optional<AuthorDto> convertToAuthorDtoOptional(Optional<Author> optionalAuthor)
    {
        return optionalAuthor.map(this::convertToAuthorDto);
    }

}
