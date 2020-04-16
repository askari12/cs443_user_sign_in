package com.example.demo;

class PasswordManager
{
    String[] encodePassword(String passwordToHash)
    {
        System.out.println(BCrypt.gensalt());
        String[] arr = new String[2];
        arr[0] = BCrypt.gensalt(12);
        arr[1] = BCrypt.hashpw(passwordToHash , arr[0]);
        return arr;
    }

    String encodePassword(String pass , String salt) {
        return BCrypt.hashpw(pass , salt);
    }

    public static void main (String[] args) {
        PasswordManager pm = new PasswordManager();

        System.out.println(pm.encodePassword("askari12" , "$2a$12$zdXRHj.bu.Nm7Jrps35Asu"));
        System.out.println("$2a$12$zdXRHj.bu.Nm7Jrps35Asun1i0nyzYFZhMv1rN3fGBzLAot7wXKKm".equals(pm.encodePassword("askari12" , "$2a$12$zdXRHj.bu.Nm7Jrps35Asu")));
    }
}
