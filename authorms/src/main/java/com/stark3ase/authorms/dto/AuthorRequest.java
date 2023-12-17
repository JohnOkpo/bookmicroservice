package com.stark3ase.authorms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stark3ase.authorms.entity.AuthorAddress;
import jakarta.validation.constraints.NotBlank;
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
