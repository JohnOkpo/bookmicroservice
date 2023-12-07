package com.stark3ase.authorms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author
{
    @Id
    private UUID authorId;
    @JsonFormat(pattern = "Firstname")
    @NotBlank(message = "Firstname shouldn't be null or empty")
    private String firstName;
    @JsonFormat(pattern = "Lastname")
    @NotBlank(message = "Lastname shouldn't be null or empty")
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorId")
    private AuthorAddress authorAddress;
}
