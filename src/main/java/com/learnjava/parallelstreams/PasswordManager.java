package com.learnjava.parallelstreams;

public class PasswordManager {
    
    public static void main(String[] args) {
        String password = "mySecurePassword";

        // Passing the password to a method
        processPassword(password);

        // Attempting to change the password (this won't work due to immutability)
        password = "newPassword";
        System.out.println("Changed password: " + password); // This will still print "mySecurePassword"
    }

    public static void processPassword(String password) {
        // Simulating some processing of the password
        System.out.println("Processing password: " + password);
    }
}
