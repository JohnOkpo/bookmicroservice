package com.stark3ase.authorms.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthorAddress
{
    @Id
    private UUID addressId;
    @Email(message = "Invalid email Id")
    private String email;
    @NotEmpty(message = "Phone number can not be null or empty")
    private String phoneNumber;
    @NotEmpty(message = "Contact Address can not be null or empty")
    private String contactAddress;
    @OneToOne
    private Author authorId;

}
