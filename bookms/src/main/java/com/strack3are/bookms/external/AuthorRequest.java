package com.strack3are.bookms.external;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorRequest
{
    private AuthorDto authorDto;
    private AuthorAddress authorAddress;
}
