package com.stark3ase.authorms.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class AuthorAddress
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID addressId;
    @Email(message = "Invalid email Id")
    private String email;
    @NotEmpty(message = "Phone number can not be null or empty")
    private String phoneNumber;
    @NotEmpty(message = "Contact Address can not be null or empty")
    private String contactAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AuthorAddress that = (AuthorAddress) o;
        return addressId != null && Objects.equals(addressId, that.addressId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
