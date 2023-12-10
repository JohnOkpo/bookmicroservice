package com.stark3ase.authorms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stark3ase.authorms.entity.AuthorAddress;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto
{
    private UUID authorId;
    @JsonFormat(pattern = "Firstname")
    @NotBlank(message = "Firstname shouldn't be null or empty")
    private String firstName;
    @JsonFormat(pattern = "Lastname")
    @NotBlank(message = "Lastname shouldn't be null or empty")
    private String lastName;
    private AuthorAddress authorAddress;
}
