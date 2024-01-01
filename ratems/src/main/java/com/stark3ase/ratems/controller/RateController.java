package com.stark3ase.ratems.controller;

import com.stark3ase.ratems.dto.RateRequest;
import com.stark3ase.ratems.exceptions.CustomExceptions;
import com.stark3ase.ratems.mapper.RateMapper;
import com.stark3ase.ratems.messaging.BookMessageProducer;
import com.stark3ase.ratems.service.impl.RateServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/rate")
@RestController
public class RateController
{
    private final RateServiceImp rateServiceImp;
    private BookMessageProducer bookMessageProducer;
    private final RateMapper rateMapper;

    public RateController(RateServiceImp rateServiceImp, BookMessageProducer bookMessageProducer, RateMapper rateMapper) {
        this.rateServiceImp = rateServiceImp;
        this.bookMessageProducer = bookMessageProducer;
        this.rateMapper = rateMapper;
    }

    @PostMapping
    public ResponseEntity<?> createRate(@RequestBody RateRequest rateRequest)
    {
        try {
            RateRequest request = rateServiceImp.createRate(rateRequest);
            bookMessageProducer.sendMessage(rateMapper.convertToRateModel(request));
            return ResponseEntity.status(HttpStatus.CREATED).body(request);
        }catch (RuntimeException ex)
        {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllRates()
    {
        try
        {
            List <RateRequest> rateRequestList = rateServiceImp.getRates();
            return ResponseEntity.status(HttpStatus.FOUND).body(rateRequestList);
        }catch (CustomExceptions.NoRatesAvailable ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRate(@PathVariable("id") UUID rateId)
    {
        try{
            RateRequest request = rateServiceImp.getRate(rateId);
            return ResponseEntity.status(HttpStatus.FOUND).body(request);
        }catch (CustomExceptions.NoRatesAvailable ex)
        {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

//    @GetMapping
//    public Integer getRatingById(@RequestParam Long bookId)
//    {
//        return rateServiceImp.getRateByBookId(bookId);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRate(@PathVariable("id") UUID rateId, @RequestBody RateRequest request)
    {
        try {
           rateServiceImp.updateRate(rateId, request);
           return ResponseEntity.status(HttpStatus.OK).body("Rate Updated Successfully");
        }catch (CustomExceptions.NullEntryException ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRate(@PathVariable("id") UUID rateId)
    {
        try {
            rateServiceImp.deleteRate(rateId);
            return ResponseEntity.status(HttpStatus.OK).body("Rate Removed Successfully");
        }catch (CustomExceptions.NullEntryException ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
