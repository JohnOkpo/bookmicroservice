package com.stark3ase.authorms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto
{
    private UUID authorId;
    @JsonFormat(pattern = "Firstname")
    @NotBlank(message = "Firstname shouldn't be null or empty")
    private String firstName;
    @JsonFormat(pattern = "Lastname")
    @NotBlank(message = "Lastname shouldn't be null or empty")
    private String lastName;
}
