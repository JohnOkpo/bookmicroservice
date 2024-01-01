package com.strack3are.bookms.dto;

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
