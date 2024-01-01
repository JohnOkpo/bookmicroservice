package com.strack3are.bookms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookModelDto
{
    @NotNull
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private UUID authorId;
    @NotNull
    private Long publisherId;
    @NotNull
    private int yOfP;
    @NotNull
    private Long isbn;
    private int rating;
}
