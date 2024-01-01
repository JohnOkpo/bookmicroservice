package com.stark3ase.ratems.messaging;

import com.stark3ase.ratems.dto.RateRequest;
import com.stark3ase.ratems.mapper.RateMapper;
import com.stark3ase.ratems.model.RateModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookMessageProducer
{
    private final RabbitTemplate rabbitTemplate;
    private final RateMapper rateMapper;

    public BookMessageProducer(RabbitTemplate rabbitTemplate, RateMapper rateMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.rateMapper = rateMapper;
    }

    public void sendMessage(RateModel rateModel)
    {
//        RateRequest rateRequestDto = rateMapper.convertToRateRequest(rateModel);
        RateRequest rateRequest = new RateRequest();

        rateRequest.setRateId(rateModel.getRateId());
        rateRequest.setReview(rateModel.getReview());
        rateRequest.setRating(rateModel.getRating());
        rateRequest.setBookId(rateModel.getBookId());
//        rateRequest.setCreatedAt(rateModel.getCreatedAt());

        rabbitTemplate.convertAndSend("bookRatingQueue",rateRequest);
    }


}
