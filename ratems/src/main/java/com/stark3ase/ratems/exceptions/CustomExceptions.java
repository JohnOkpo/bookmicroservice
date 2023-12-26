package com.stark3ase.ratems.exceptions;

public class CustomExceptions
{
    public static class NullEntryException extends RuntimeException
    {
        public NullEntryException (String message)
        {
            super(message);
        }
    }

    public static class NoRatesAvailable extends RuntimeException
    {
        public NoRatesAvailable(String message)
        {
            super(message);
        }
    }

    public static class RateNotFoundException extends RuntimeException
    {
        public RateNotFoundException(String message)
        {
            super(message);
        }
    }
}
