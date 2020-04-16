package com.example.demo;

class PasswordManager
{
    String encodePassword(String passwordToHash)
    {
        return BCrypt.hashpw(passwordToHash, BCrypt.gensalt(12));
    }
}
