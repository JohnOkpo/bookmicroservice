package com.stark3ase.ratems.config;

import com.stark3ase.ratems.exceptions.CustomExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdviceExceptionHandler
{
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleRuntimeException(RuntimeException runtimeException)
    {
        Map<String, String> errorMap = new HashMap<>();
        List<Map<String, String>> errorList = new ArrayList<>();
//        errorMap.put("error message", "Please provide a valid input");
        errorMap.put("error message", runtimeException.getMessage());
        return errorMap;
    }

    @ExceptionHandler(CustomExceptions.NoRatesAvailable.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, String> handleNoRateAvailable(CustomExceptions.NoRatesAvailable ex)
    {
        Map<String, String> errorMap = new HashMap<>();
        List<Map<String, String>> errorList = new ArrayList<>();
        errorMap.put("error message", "No result found");
        return errorMap;
    }
}
