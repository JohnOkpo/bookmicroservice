package com.stark3ase.ratems.mapper;

import com.stark3ase.ratems.dto.RateRequest;
import com.stark3ase.ratems.model.RateModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RateMapper
{
    private final ModelMapper modelMapper;

    public RateMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RateModel convertToRateModel(RateRequest rateRequest)
    {
        return modelMapper.map(rateRequest, RateModel.class);
    }

    public RateRequest convertToRateRequest(RateModel rateModel)
    {
        return modelMapper.map(rateModel, RateRequest.class);
    }

    public List<RateModel> convertToList(List<RateRequest> rateRequestList)
    {
        return rateRequestList.stream()
                .map(this::convertToRateModel)
                .collect(Collectors.toList());
    }

    public List<RateRequest> convertToRateRequest(List<RateModel> rateModelList)
    {
        return rateModelList.stream()
                .map(this::convertToRateRequest)
                .collect(Collectors.toList());
    }

    public RateRequest convertToOptional(Optional<RateModel> optionalRateModel)
    {
        return optionalRateModel.map(this::convertToRateRequest).orElse(null);
    }

}
