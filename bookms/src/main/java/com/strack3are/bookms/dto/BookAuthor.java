package com.strack3are.bookms.dto;

import com.strack3are.bookms.external.AuthorRequest;
import lombok.Data;

@Data
public class BookAuthor
{
    BookModelDto bookModelDto;
    AuthorRequest authorRequest;
}
