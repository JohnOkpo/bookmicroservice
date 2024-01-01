package com.stark3ase.ratems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookModelDto
{
    private Long id;
    private String title;
    private UUID authorId;
    private Long publisherId;
    private int yOfP;
    private Long isbn;
    private int rating;
}
