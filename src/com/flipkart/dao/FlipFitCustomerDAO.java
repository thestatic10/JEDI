package com.flipkart.dao;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;
import com.flipkart.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.flipkart.constants.SQLConstants.*;

public class FlipFitCustomerDAO implements FlipFitCustomereDAOInterface {

    public void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber) throws RegistrationFailedException {
        try {
            Connection conn = DatabaseConnector.connect();
            PreparedStatement stmt = conn.prepareStatement(ADD_NEW_CUSTOMER);
            stmt.setString(1, userName);
            stmt.setString(2, userName);
            stmt.setString(3, password);
            stmt.setString(4, email);
            stmt.setString(5, phoneNumber);
            stmt.setString(6, cardNumber);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException exp) {
            throw new RegistrationFailedException("Failed to register the user. Try again.");
        } catch (Exception e) {
            System.out.println("Oops! An error occurred. Try again later.");
        }
    }

    public boolean isUserValid(String userName, String password) throws UserInvalidException {
        try {
            Connection conn = DatabaseConnector.connect();
            PreparedStatement stmt = conn.prepareStatement(CUSTOMER_LOGIN_QUERY);
            stmt.setString(1, userName);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                stmt.close();
                return true;
            }
            stmt.close();
        } catch (SQLException exp) {
            throw new UserInvalidException("User is Invalid. Try again.");
        } catch (Exception exp) {
            System.out.println("Oops! An error occurred. Try again later.");
        }
        return false;
    }

    public FlipFitCustomer getCustomerById(String userName) {
        FlipFitCustomer customer = new FlipFitCustomer();
        try {
            Connection conn = DatabaseConnector.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_CUSTOMER_BY_ID);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            customer.setEmail(rs.getString("email"));
            customer.setUserID(rs.getString("Id"));
            customer.setPassword(rs.getString("password"));
            customer.setUserName(rs.getString("name"));
            customer.setCustomerPhone(rs.getString("phone"));
            customer.setCardDetails(rs.getString("cardDetails"));

            stmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        }

        return customer;
    }
//    private Map<String, Customer> customers = new HashMap<>();
//
//
//    public void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber) {
//        if (customers.containsKey(userName)) {
//            System.out.println("User already exists.");
//        }
//        Customer customer = new Customer(UUID.randomUUID().toString(), userName, password, email, phoneNumber, cardNumber);
//        customers.put(userName, customer);
//    }
//
//    public boolean isUserValid(String userName, String password) {
//        Customer customer = customers.get(userName);
//        if (customer != null && customer.getPassword().equals(password)) {
//            return true;
//        } else {
//            System.out.println("User is Invalid. Try again.");
//            return false;
//        }
//    }
//
//    public Customer getCustomerById(String userName) {
//        return customers.get(userName);
//    }
}

