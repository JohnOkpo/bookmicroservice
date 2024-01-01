package com.stark3ase.ratems.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RateRequest
{

    private UUID rateId;
    private int rating;
    private Long bookId;
    private String review;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
