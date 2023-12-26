package com.stark3ase.ratems.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID rateId;
    private int rating;
    private UUID bookId;
    private String review;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
