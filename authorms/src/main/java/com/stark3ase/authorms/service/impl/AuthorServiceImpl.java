package com.stark3ase.authorms.service.impl;

import com.stark3ase.authorms.dto.AuthorDto;
import com.stark3ase.authorms.dto.AuthorRequest;
import com.stark3ase.authorms.entity.Author;
import com.stark3ase.authorms.entity.AuthorAddress;
import com.stark3ase.authorms.exceptions.AuthorCustomException;
import com.stark3ase.authorms.mapper.AuthorMapper;
import com.stark3ase.authorms.repository.AuthorAddressRepository;
import com.stark3ase.authorms.repository.AuthorRepository;
import com.stark3ase.authorms.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService
{
    private final AuthorRepository authorRepository;
    private final AuthorAddressRepository authorAddressRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository,
    AuthorMapper authorMapper,
    AuthorAddressRepository authorAddressRepository )
    {
        this.authorRepository = authorRepository;
        this.authorAddressRepository = authorAddressRepository;
        this.authorMapper = authorMapper;
    }


    @Override
    public AuthorRequest createNewAuthor(AuthorRequest authorRequest) {

        if(authorRequest.getAuthorAddress().getEmail().isEmpty()  ||
                authorRequest.getAuthorDto().getFirstName().isEmpty() )
        {
            throw new AuthorCustomException.AuthorNotCreatedException("Please provide a valid request");
        }

        AuthorDto authorDto = authorRequest.getAuthorDto();
        AuthorAddress authorAddress = authorRequest.getAuthorAddress();
        AuthorAddress authorResponse = authorAddressRepository.save(authorAddress);
        UUID authorAddressId = authorResponse.getAddressId();
        Author incomingAuthor = authorMapper.convertToAuthor(authorDto);
        incomingAuthor.setAuthorAddress(authorAddressId);
        Author newAuthor = authorRepository.save(incomingAuthor);
        AuthorDto savedAuthorDto = authorMapper.convertToAuthorDto(newAuthor);
        return new AuthorRequest(savedAuthorDto, authorAddress);
    }

    @Override
    public AuthorRequest getAuthor(UUID authorId) {

        if(authorId == null)
        {
            throw new RuntimeException("Please provide a valid ID");
        }


        Author authorDb = authorRepository.getAuthorByAuthorId(authorId);
        if(authorDb != null)
        {

            UUID authorAddressId = authorDb.getAuthorAddress();
            AuthorAddress authorAddress = authorAddressRepository.getAuthorAddressByAddressId(authorAddressId);
            AuthorDto authorDto = authorMapper.convertToAuthorDto(authorDb);
            return new AuthorRequest(authorDto, authorAddress);
        }
        else
        {
            throw new RuntimeException("Author does not exist");
        }
    }

    @Override
    public List<AuthorRequest> getAllAuthor() {
        List<Author> listOfAuthors = authorRepository.findAll();
        List<AuthorRequest> authorRequests = new ArrayList<>();

        for(Author author : listOfAuthors)
        {
            UUID authorAddressId = author.getAuthorAddress();
            AuthorAddress authorAddress = authorAddressRepository.getAuthorAddressByAddressId(authorAddressId);
            authorRequests.add(new AuthorRequest(authorMapper.convertToAuthorDto(author), authorAddress));
        }
        return authorRequests;
    }

    @Override
    public Boolean deleteAuthor(UUID authorId) {
        if(authorId == null)
        {
            throw new RuntimeException("Please provide a valid ID");
        }
        Author authorDb = authorRepository.getAuthorByAuthorId(authorId);
        if(authorDb != null)
        {
            UUID authorIdFromDb = authorDb.getAuthorAddress();
            authorAddressRepository.deleteById(authorIdFromDb);
            authorRepository.delete(authorDb);
            return true;
        }
        else
        {
            throw  new RuntimeException("user does not exist");
        }

    }

    @Override
    public AuthorRequest updateAuthor(UUID authorId, AuthorRequest authorRequest) {
        if(authorId == null)
        {
            throw new RuntimeException("Please provide a valid ID");
        }
        Author  isAuthorPresent = authorRepository.getAuthorByAuthorId(authorId);
        if(isAuthorPresent != null)
        {
            UUID  authorAddressId = isAuthorPresent.getAuthorAddress();

            AuthorDto authorDto = authorRequest.getAuthorDto();
            isAuthorPresent.setFirstName(authorDto.getFirstName());
            isAuthorPresent.setLastName(authorDto.getLastName());

            AuthorAddress authorAddress = authorRequest.getAuthorAddress();
            AuthorAddress fetchAuthorAddress = authorAddressRepository.getAuthorAddressByAddressId(authorAddressId);
            fetchAuthorAddress.setContactAddress(authorAddress.getContactAddress());
            fetchAuthorAddress.setEmail(authorAddress.getEmail());
            fetchAuthorAddress.setPhoneNumber(authorAddress.getPhoneNumber());
            fetchAuthorAddress.setAddressId(authorAddressId);
            AuthorAddress updatedRecordAddress = authorAddressRepository.save(fetchAuthorAddress);
            Author updatedAuthorRecord = authorRepository.save(isAuthorPresent);
            AuthorDto authorDto1 = authorMapper.convertToAuthorDto(updatedAuthorRecord);
            return new AuthorRequest(authorDto1, updatedRecordAddress);

        }else
        {
            throw new RuntimeException("Author does not exist");
        }

    }

    @Override
    public AuthorRequest patchAuthor(UUID authorId, AuthorRequest authorRequest)
    {
        if(authorId == null)
        {
            throw new RuntimeException("Please provide a valid ID");
        }

        Author existingAuthor = authorRepository.getAuthorByAuthorId(authorId);
        AuthorAddress existingAddress = authorAddressRepository.getAuthorAddressByAddressId(existingAuthor.getAuthorAddress());

        AuthorAddress authorAddressUpdates = authorRequest.getAuthorAddress();
        AuthorDto authorDto = authorRequest.getAuthorDto();

        if(authorDto != null)
        {
            if(authorDto.getFirstName() != null)
            {
                existingAuthor.setFirstName(authorDto.getFirstName());
            }

            if(authorDto.getLastName() != null)
            {
                existingAuthor.setLastName(authorDto.getLastName());
            }
        }

        if(authorAddressUpdates != null)
        {
            if(authorAddressUpdates.getContactAddress() != null)
            {
                existingAddress.setContactAddress(authorAddressUpdates.getContactAddress());
            }

            if(authorAddressUpdates.getEmail() != null)
            {
                existingAddress.setEmail(authorAddressUpdates.getEmail());
            }

            if(authorAddressUpdates.getPhoneNumber() != null)
            {
                existingAddress.setPhoneNumber(authorAddressUpdates.getPhoneNumber());
            }

        }

        AuthorAddress updatedAuthorAddress = authorAddressRepository.save(existingAddress);
        Author updatedAuthor = authorRepository.save(existingAuthor);
        return new AuthorRequest(authorMapper.convertToAuthorDto(updatedAuthor), updatedAuthorAddress);
    }
}
