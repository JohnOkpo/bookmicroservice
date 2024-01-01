package com.strack3are.bookms.external;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class Author
{
    private UUID authorId;
    @JsonFormat(pattern = "Firstname")
    @NotBlank(message = "Firstname shouldn't be null or empty")
    private String firstName;
    @JsonFormat(pattern = "Lastname")
    @NotBlank(message = "Lastname shouldn't be null or empty")
    private String lastName;
    @Column(name = "author_address_id")
    private UUID authorAddress;
}
