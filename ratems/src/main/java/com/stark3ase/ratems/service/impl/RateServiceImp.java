package com.stark3ase.ratems.service.impl;

import com.stark3ase.ratems.dto.RateRequest;
import com.stark3ase.ratems.exceptions.CustomExceptions;
import com.stark3ase.ratems.mapper.RateMapper;
import com.stark3ase.ratems.model.RateModel;
import com.stark3ase.ratems.repository.RateRepository;
import com.stark3ase.ratems.service.RateService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RateServiceImp implements RateService
{
    private final RateRepository rateRepository;
    private final RateMapper rateMapper;
    public RateServiceImp(RateRepository rateRepository,
    RateMapper rateMapper)
    {
        this.rateRepository = rateRepository;
        this.rateMapper = rateMapper;
    }

    @Override
    public RateRequest createRate(RateRequest rateRequest) {
        validateRequest(rateRequest);
        rateRequest.setCreatedAt(LocalDateTime.now());
        RateModel savedRequest = rateRepository.save(rateMapper.convertToRateModel(rateRequest));
        return rateMapper.convertToRateRequest(savedRequest);
    }

    @Override
    public List<RateRequest> getRates() {
        List<RateModel> rateModelList = rateRepository.findAll();
        if(rateModelList.size() == 0)
        {
            throw new CustomExceptions.NoRatesAvailable("No result found");
        }
        return rateMapper.convertToRateRequest(rateModelList);
    }

    @Override
    public RateRequest getRate(UUID rateId) {
       Optional<RateModel> rateModel = rateRepository.findById(rateId);
        if(rateModel.isEmpty())
        {
            throw new CustomExceptions.NoRatesAvailable("No result found");
        }
        return rateMapper.convertToOptional(rateModel);
    }

    @Override
    public void deleteRate(UUID rateId)
    {
        if(rateId == null)
        {
            throw new CustomExceptions.RateNotFoundException("rate does not exist");
        }
        rateRepository.deleteById(rateId);
    }

    @Override
    public void updateRate(UUID rateId, RateRequest rateRequest)
    {
        if(rateId == null)
        {
            throw new CustomExceptions.RateNotFoundException("rate does not exist");
        }
        Optional<RateModel> rateModel = rateRepository.findById(rateId);
        RateRequest rateModelFromDb = rateMapper.convertToOptional(rateModel);
        rateModelFromDb.setRating(rateRequest.getRating());
        rateModelFromDb.setBookId(rateRequest.getBookId());
        rateModelFromDb.setReview(rateRequest.getReview());
        rateModelFromDb.setUpdatedAt(LocalDateTime.now());
        rateRepository.save(rateMapper.convertToRateModel(rateModelFromDb));
    }

    private void validateRequest(RateRequest rateRequest)
    {
        if(rateRequest.getRating() < 0)
        {
            throw new RuntimeException("Please provide valid input");
        }

        if(rateRequest.getBookId() == null)
        {
            throw new RuntimeException("Please provide valid input");
        }

        if(rateRequest.getReview().isEmpty() || rateRequest.getReview() == null)
        {
            throw new RuntimeException("Please provide valid input");
        }

    }
}
