package com.stark3ase.ratems.service;

import com.stark3ase.ratems.dto.RateRequest;

import java.util.List;
import java.util.UUID;

public interface RateService
{
    RateRequest createRate(RateRequest rateRequest);
    List<RateRequest> getRates();
    RateRequest getRate(UUID rateId);
    void deleteRate(UUID rateId);
    void updateRate(UUID rateId, RateRequest rateRequest);
}
