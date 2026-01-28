package com.flipkart.dao;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;

/**
 * Data Access Object (DAO) interface for Customer operations.
 * 
 * @author gamma-group
 */
public interface FlipFitCustomereDAOInterface {

    /**
     * Register customer.
     * 
     * @param userName    Username
     * @param password    Password
     * @param email       Email
     * @param phoneNumber Phone Number
     * @param cardNumber  Card Number
     * @throws RegistrationFailedException If registration fails
     */
    void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber)
            throws RegistrationFailedException;

    /**
     * Validates if a user is valid.
     * 
     * @param userName Username
     * @param password Password
     * @return True if valid
     * @throws UserInvalidException If validation fails
     */
    boolean isUserValid(String userName, String password) throws UserInvalidException;

    /**
     * Get customer by ID.
     * 
     * @param userName Username
     * @return Customer object
     */
    FlipFitCustomer getCustomerById(String userName);

    /**
     * Updates the password in the database for a specific user.
     * 
     * @param userName    Username
     * @param oldPassword Old Password
     * @param newPassword New Password
     * @return true if update was successful, false otherwise
     */
    boolean changePassword(String userName, String oldPassword, String newPassword);
}