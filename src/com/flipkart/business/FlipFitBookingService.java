package com.flipkart.business;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.dao.FlipFitBookingDAO;
import com.flipkart.exceptions.BookingFailedException;
import com.flipkart.utils.UserPlan;

import java.util.Date;
import java.util.List;

/**
 * Service class for Booking operations.
 * 
 * @author gamma-group
 */
public class FlipFitBookingService implements FlipFitBookingInterface {

    private final FlipFitBookingDAO bookingDAO = new FlipFitBookingDAO();
    private final FlipFitScheduleService scheduleService = new FlipFitScheduleService();
    private final FlipFitSlotService slotService = new FlipFitSlotService();

    /**
     * Checks if there is any overlap for the booking.
     * 
     * @param customerId Customer ID
     * @param date       Date of booking
     * @param slotId     Slot ID
     * @return True if overlap exists, false otherwise
     */
    public boolean checkBookingOverlap(String customerId, Date date, String slotId) {
        FlipFitSlot slot = slotService.getSlotByID(slotId);
        return bookingDAO.checkBookingOverlap(customerId, date, slot.getTime());
    }

    /**
     * Adds a booking.
     * 
     * @param userName   Username of the user
     * @param scheduleID Schedule ID
     */
    public void addBooking(String userName, String scheduleID) {
        try {
            boolean isAvailable = scheduleService.modifySchedule(scheduleID, -1);
            if (!isAvailable) {
                System.out.println("No seats available for the booking");
                return;
            }
            bookingDAO.addBooking(userName, scheduleID);
        } catch (BookingFailedException e) {
            System.out.println("Failed to add booking");
        }
    }

    /**
     * Get bookings by customer ID.
     * 
     * @param customerId Customer ID
     * @return List of bookings
     */
    public List<FlipFitBooking> getBookingByCustomerId(String customerId) {
        try {
            return bookingDAO.getBookingByCustomerId(customerId);
        } catch (BookingFailedException e) {
            System.out.println("Failed to get booking");
        }
        return null;
    }

    /**
     * Get customer plan.
     * 
     * @param customerId Customer ID
     * @return List of user plans
     */
    public List<UserPlan> getCustomerPlan(String customerId) {
        return bookingDAO.getCustomerPlan(customerId);
    }

    /**
     * Cancel a booking by ID.
     * 
     * @param bookingID Booking ID to cancel
     */
    public void cancelBooking(String bookingID) {
        try {
            FlipFitBooking booking = bookingDAO.getBookingByBookingId(bookingID);
            bookingDAO.cancelBookingById(bookingID);
            scheduleService.modifySchedule(booking.getScheduleID(), 1);
        } catch (BookingFailedException e) {
            System.out.println("Failed to cancel booking");
        }
    }

}
