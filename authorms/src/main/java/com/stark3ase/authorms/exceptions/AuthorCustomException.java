package com.stark3ase.authorms.exceptions;

public class AuthorCustomException
{
    public static class AuthorNotCreatedException extends RuntimeException
    {
        public AuthorNotCreatedException(String message)
        {
            super(message);
        }
    }
}
