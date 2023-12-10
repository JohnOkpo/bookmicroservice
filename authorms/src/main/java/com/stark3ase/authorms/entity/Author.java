package com.stark3ase.authorms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Author
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID authorId;
    @JsonFormat(pattern = "Firstname")
    @NotBlank(message = "Firstname shouldn't be null or empty")
    private String firstName;
    @JsonFormat(pattern = "Lastname")
    @NotBlank(message = "Lastname shouldn't be null or empty")
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_address_id")
    @JsonBackReference
    private AuthorAddress authorAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Author author = (Author) o;
        return authorId != null && Objects.equals(authorId, author.authorId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
