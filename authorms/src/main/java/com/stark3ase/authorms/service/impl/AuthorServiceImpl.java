package com.stark3ase.authorms.service.impl;

import com.stark3ase.authorms.dto.AuthorDto;
import com.stark3ase.authorms.entity.Author;
import com.stark3ase.authorms.mapper.AuthorMapper;
import com.stark3ase.authorms.repository.AuthorRepository;
import com.stark3ase.authorms.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService
{
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository,
    AuthorMapper authorMapper)
    {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }


    @Override
    public AuthorDto createNewAuthor(AuthorDto authorDto) {
        Author incomingDto = authorMapper.convertToAuthor(authorDto);
        Author newAuthor = authorRepository.save(incomingDto);
        AuthorDto newAuthorDto = authorMapper.convertToAuthorDto(newAuthor);
        return newAuthorDto;
    }

    @Override
    public AuthorDto getAuthor(UUID authorId) {
        Author authorDb = authorRepository.getAuthorByAuthorId(authorId);
        if(authorDb != null)
        {
            return authorMapper.convertToAuthorDto(authorDb);
        }
        else
        {
            throw new RuntimeException("Author does not exist");
        }
    }

    @Override
    public List<AuthorDto> getAllAuthor() {
        List<Author> listOfAuthors = authorRepository.findAll();
        List<AuthorDto> listOfAuthorsDto = new ArrayList<>();

        for (Author author : listOfAuthors
             ) {
                listOfAuthorsDto.add(authorMapper.convertToAuthorDto(author));
              }
        return listOfAuthorsDto;
    }

    @Override
    public Boolean deleteAuthor(UUID authorId) {
        Author authorDb = authorRepository.getAuthorByAuthorId(authorId);
        if(authorDb != null)
        {
            authorRepository.delete(authorDb);
            return true;
        }
        else
        {
            throw  new RuntimeException("user does not exist");
        }

    }

    @Override
    public AuthorDto updateAuthor(UUID authorId, AuthorDto authorDto) {
        Author  isAuthorPresent = authorRepository.getAuthorByAuthorId(authorId);
        if(isAuthorPresent != null)
        {
            authorDto.setFirstName(isAuthorPresent.getFirstName());
            authorDto.setLastName(isAuthorPresent.getLastName());
            authorDto.setAuthorAddress(isAuthorPresent.getAuthorAddress());
            Author updatedAuthorRecord = authorRepository.save(authorMapper.convertToAuthor(authorDto));
            AuthorDto updatedAuthorRecordDto = authorMapper.convertToAuthorDto(updatedAuthorRecord);
            return updatedAuthorRecordDto;
        }else
        {
            throw new RuntimeException("Author does not exist");
        }

    }

    @Override
    public AuthorDto patchAAuthor(UUID authorId, AuthorDto authorDto) {
        return null;
    }
}
