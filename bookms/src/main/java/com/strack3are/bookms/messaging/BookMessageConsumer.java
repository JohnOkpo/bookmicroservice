package com.strack3are.bookms.messaging;

import com.strack3are.bookms.dto.RateRequest;
import com.strack3are.bookms.service.impl.BookServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookMessageConsumer
{
    private final BookServiceImpl bookService;

    public BookMessageConsumer(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @RabbitListener(queues = "bookRatingQueue")
    public void consumeMessage(RateRequest rateRequest)
    {
        bookService.updateRating(rateRequest);
    }
}
