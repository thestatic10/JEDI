package com.flipkart.business;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.dao.FlipFitCustomerDAO;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;
import com.flipkart.utils.UserPlan;

import java.util.Date;
import java.util.List;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * Service class for Customer operations.
 * 
 * @author gamma-group
 */
public class FlipFitCustomerService implements FlipFitCustomerInterface {

    private FlipFitGymCenterInterface gymCentreService = new FlipFitGymCenterService();
    private FlipFitBookingInterface bookingService = new FlipFitBookingService();
    private FlipFitScheduleInterface scheduleService = new FlipFitScheduleService();
    private FlipFitSlotInterface slotService = new FlipFitSlotService();
    private FlipFitCustomerDAO customerDAO = new FlipFitCustomerDAO();

    /**
     * Get all gym center details by city.
     * 
     * @param city City name
     * @return List of gym centers
     */
    public List<FlipFitGymCenter> getAllGymCenterDetailsByCity(String city) {
        // takes City (Location) as input and returns List<GymCenter>
        return gymCentreService.getCentresByCity(city);
    }

    /**
     * Get available slots.
     * 
     * @param centreID Center ID
     * @param date     Date
     * @return List of available slots
     */
    @Override
    public List<FlipFitSlot> getAvailableSlots(String centreID, Date date) {
        return gymCentreService.getAvailableSlotsByCentreAndDate(centreID, date);
    }

    /**
     * Get customer bookings.
     * 
     * @param customerId Customer ID
     * @return List of bookings
     */
    public List<FlipFitBooking> getCustomerBookings(String customerId) {
        // takes userId and returns List<Bookings>
        return bookingService.getBookingByCustomerId(customerId);
    }

    /**
     * Cancel booking by ID.
     * 
     * @param bookingID Booking ID
     */
    @Override
    public void cancelBookingByID(String bookingID) {
        bookingService.cancelBooking(bookingID);
    }

    /**
     * Get customer plan.
     * 
     * @param customerId Customer ID
     * @return List of user plans
     */
    public List<UserPlan> getCustomerPlan(String customerId) {
        return bookingService.getCustomerPlan(customerId);
    }

    /**
     * Get customer plan for a specific date.
     * 
     * @param customerId Customer ID
     * @param date       Date
     * @return List of selected user plans
     */
    public List<UserPlan> getCustomerPlan(String customerId, Date date) {
        List<UserPlan> allPlan = bookingService.getCustomerPlan(customerId);
        List<UserPlan> selectedPlan = new ArrayList<>();
        allPlan.forEach(userPlan -> {
            if (userPlan.getDate().equals(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
                selectedPlan.add(userPlan);
            }
        });
        return selectedPlan;
    }

    /**
     * Book various slots.
     * 
     * @param userName Username
     * @param date     Date
     * @param slotId   Slot ID
     * @param centreId Center ID
     * @return True if successful, false otherwise
     */
    public boolean bookSlot(String userName, Date date, String slotId, String centreId) {
        if (!slotService.isSlotValid(slotId, centreId)) {
            System.out.println("INVALID SLOT");
            return false;
        }
        String scheduleId = scheduleService.getOrCreateSchedule(slotId, date).getScheduleID();
        // create booking
        boolean isOverlap = bookingService.checkBookingOverlap(userName, date, slotId);
        if (isOverlap) {
            System.out.println("There exists a conflicting booking, First cancel it!!!!");
            return false;
        }
        bookingService.addBooking(userName, scheduleId);
        return true;
    }

    /**
     * Cancel booking by ID.
     * 
     * @param bookingID Booking ID
     */
    public void cancelBookingbyID(String bookingID) {
        // cancel a booking
        bookingService.cancelBooking(bookingID);
    }

    /**
     * Register a new customer.
     * 
     * @param userName    Username
     * @param password    Password
     * @param email       Email
     * @param phoneNumber Phone number
     * @param cardNumber  Card number
     */
    public void registerCustomer(String userName, String password, String email, String phoneNumber,
            String cardNumber) {
        try {
            customerDAO.registerCustomer(userName, password, email, phoneNumber, cardNumber);
        } catch (RegistrationFailedException e) {
            e.getMessage();
        }
    }

    /**
     * View profile.
     * 
     * @param userName Username
     * @return FlipFitCustomer profile
     */
    @Override
    public FlipFitCustomer viewMyProfile(String userName) {
        return customerDAO.getCustomerById(userName);
    }

    /**
     * Validate user.
     * 
     * @param userName Username
     * @param password Password
     * @return True if valid, false otherwise
     */
    public boolean isUserValid(String userName, String password) {
        try {
            return customerDAO.isUserValid(userName, password);
        } catch (UserInvalidException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
