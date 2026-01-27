package com.flipkart.dao;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;

/**
 * Data Access Object (DAO) interface for handling customer-related operations in FlipFit system.
 * This interface defines methods for registering customers, validating users, and retrieving customer details.
 */
public interface FlipFitCustomereDAOInterface {

    /**
     * Registers a new customer with the provided details.
     * @param userName Username of the customer
     * @param password Password of the customer
     * @param email Email of the customer
     * @param phoneNumber Phone number of the customer
     * @param cardNumber Card number of the customer
     * @throws RegistrationFailedException If registration fails for any reason
     */
    void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber) throws RegistrationFailedException;

    /**
     * Checks if a user with the given username and password is valid.
     * @param userName Username of the user
     * @param password Password of the user
     * @return True if the user is valid, false otherwise
     * @throws UserInvalidException If user validation fails
     */
    boolean isUserValid(String userName, String password) throws UserInvalidException;

    /**
     * Retrieves details of a customer based on their username.
     * @param userName Username of the customer
     * @return The FlipFitCustomer object
     */
    FlipFitCustomer getCustomerById(String userName);
}
