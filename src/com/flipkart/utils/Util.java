package com.flipkart.utils;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitSlot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Utility class for printing and date parsing.
 * 
 * @author gamma-group
 */
public class Util {

    /**
     * Print gym centers.
     * 
     * @param centreListByLocation List of gym centers
     */
    public static void printGymCentres(List<FlipFitGymCenter> centreListByLocation) {
        System.out.println("-----");
        System.out.printf("%-8s\t", "Centre-ID");
        System.out.printf("%-8s\t", "OWNER-ID");
        System.out.printf("%-8s\t", "CENTER-NAME");
        System.out.printf("%8s\t", "GST-IN");
        System.out.printf("%-8s\t", "CITY");
        System.out.printf("%-8s\t", "CAPACITY");
        System.out.printf("%-8s\t", "PRICE");
        System.out.printf("%-8s\t\n", "APPROVED");
        System.out.println("-----");
        // java
        centreListByLocation.forEach(gymCenter -> {
            System.out.printf("%-8s\t", gymCenter.getGymCenterId());
            System.out.printf("%-8s\t", gymCenter.getOwnerID());
            System.out.printf("%-8s\t", gymCenter.getGymCenterName());
            System.out.printf("%-8s\t", gymCenter.getGstin());
            System.out.printf("%-8s\t", gymCenter.getCity());
            System.out.printf("%-8s\t", gymCenter.getCapacity());
            System.out.printf("%-8s\t", gymCenter.getPrice());
            System.out.printf("%-8s\t\n", gymCenter.isApproved());
        });

        System.out.println("-----");
    }

    /**
     * Print owner list.
     * 
     * @param gymOwnerList List of gym owners
     */
    public static void printOwnerList(List<FlipFitGymOwner> gymOwnerList) {
        System.out.println("-----");
        System.out.printf("%-8s\t", "ID");
        System.out.printf("%-8s\t", "NAME");
        System.out.printf("%-8s\t", "EMAIL-ID");
        System.out.printf("%11s\t", "PAN");
        System.out.printf("%23s\t\n", "IS-APPROVED");
        System.out.println("-----");
        System.out.println("");
        gymOwnerList.forEach(gymOwner -> {
            System.out.printf("%-8s\t", gymOwner.getUserID());
            System.out.printf("%-8s\t", gymOwner.getUserName());
            System.out.printf("%-8s\t", gymOwner.getEmail());
            System.out.printf("%-8s\t", gymOwner.getPanNumber());
            if (gymOwner.isApproved()) {
                System.out.println("Yes\n");
            } else if (!gymOwner.isApproved()) {
                System.out.println("No\n");
            } else {
                System.out.println("Pending\n");
            }
        });
        System.out.println("----");
    }

    /**
     * Print customer profile.
     * 
     * @param customer Customer object
     */
    public static void printCustomerProfile(FlipFitCustomer customer) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("USER ID: " + customer.getUserID());
        System.out.println("USER NAME: " + customer.getUserName());
        System.out.println("EMAIL: " + customer.getEmail());
        System.out.println("CONTACT: " + customer.getCustomerPhone());
        System.out.println("CARD DETAILS: " + customer.getCardDetails());
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Print slots.
     * 
     * @param slots List of slots
     */
    public static void printSlots(List<FlipFitSlot> slots) {
        System.out.println("-----");
        System.out.printf("%-8s\t", "SLOT-ID");
        System.out.printf("%-8s\t\n", "SLOT-TIME");
        System.out.println("----");
        slots.forEach(slot -> {
            System.out.printf("%-8s\t", slot.getSlotId());
            System.out.printf("%-8s\t\n", slot.getTime());
        });
        System.out.println("----");
    }

    /**
     * Select date with input.
     * 
     * @return Date (java.util.Date)
     * @throws ParseException If parsing fails
     */
    public static Date selectDate() throws ParseException {
        System.out.print("Enter Date (dd/MM/yyyy): ");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String dateStr = scanner.next();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(dateStr);
    }
}
