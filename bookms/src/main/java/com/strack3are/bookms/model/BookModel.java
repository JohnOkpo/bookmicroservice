package com.strack3are.bookms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Table(name = "BookModel_TB")
public class BookModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
