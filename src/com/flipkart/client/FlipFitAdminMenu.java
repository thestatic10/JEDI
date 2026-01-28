package com.flipkart.client;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.business.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Scanner;

import static com.flipkart.utils.Util.*;

/**
 * Menu class for Admin operations.
 * 
 * @author gamma-group
 */
public class FlipFitAdminMenu {
    private static FlipFitAdmin admin = new FlipFitAdmin();
    private static FlipFitAdminInterface adminService = new FlipFitAdminService();
    private static FlipFitGymOwnerInterface gymOwnerService = new FlipFitGymOwnerService();
    private static FlipFitGymCenterInterface gymCenterService = new FlipFitGymCenterService();
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Admin login.
     * 
     * @param userName Username
     * @param password Password
     * @return True if login successful
     */
    public boolean adminLogin(String userName, String password) {
        if (adminService.isUserValid(userName, password)) {
            System.out.println("Successfully logged in");
            adminClientMainPage();
            return true;
        } else {
            System.out.println("Login Failed");
            return false;
        }
    }

    /**
     * Register a new admin.
     */
    public void register() {
        System.out.println("Enter email:");
        String email = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();
        System.out.println("Enter username:");
        String userName = scanner.next();

        adminService.registerAdmin(userName, password, email);
        System.out.println("Admin registered successfully!");
    }

    /**
     * Handle gym owner approval requests.
     */
    private void handleGymOwnerApprovalRequests() {
        // print the list with indexes from 1
        System.out.println("Admin Approval for a Gym Owner ----------");

        System.out.println("(Press 0 to exit)\nEnter the Id of Gym Owner:");
        String requestGymOwnerId = scanner.next();

        if (requestGymOwnerId.equals("0")) {
            return;
        }

        System.out.println("1. Approve the request\n2. Reject the request");
        int choice = scanner.nextInt();
        if (choice == 1) {
            adminService.approveGymOwner(requestGymOwnerId, true);
            System.out.println("Approved Gym Owner");
        } else if (choice == 2) {
            adminService.approveGymOwner(requestGymOwnerId, false);
            System.out.println("Disapproved Gym Owner");
        }

    }

    /**
     * Handle gym center approval requests.
     */
    private void handleGymCenterApprovalRequests() {
        // print the list with indexes from 1
        System.out.println("Press 0 to Exit or Choose the Gym Centre To Modify:");
        String requestGymCenterId = scanner.next();
        if (requestGymCenterId.equals("0"))
            return;
        // Now Admin will select an request and we will pop up with two
        System.out.println("1. Approve the request\n2. Reject the request\n");
        int choice = scanner.nextInt();
        if (choice == 1) {
            adminService.approveGymCenter(requestGymCenterId, true);
            System.out.println("Approved Gym Centre");
        } else if (choice == 2) {
            adminService.approveGymCenter(requestGymCenterId, false);
            System.out.println("Disapproved Gym Centre");
        }
        // modify the list
        // adminClientMainPage();
    }

    /**
     * Main page for Admin.
     */
    public void adminClientMainPage() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("Welcome ADMIN to FlipFit Application\nLogin Date: " + currentTime.format(dateFormat)
                + "\nLogin Time: " + currentTime.format(timeFormat));
        while (true) {
            System.out.println(
                    "1. View Approved Gym Owners\n2. View Unapproved Gym Owners\n3. View Approved Gym Centers\n4. View Unapproved Gym Centers\n5. View All Gym Owners\n6. View All Gym Centers\n7. Go Back To Previous Menu");
            int pendingChoice = scanner.nextInt();
            switch (pendingChoice) {
                case 1:
                    List<FlipFitGymOwner> approvedGymOwners = adminService.getApprovedGymOwners();
                    printOwnerList(approvedGymOwners);
                    break;
                case 2:
                    List<FlipFitGymOwner> unapprovedGymOwners = adminService.getUnapprovedGymOwners();
                    printOwnerList(unapprovedGymOwners);
                    if (!unapprovedGymOwners.isEmpty())
                        handleGymOwnerApprovalRequests();
                    break;
                case 3:
                    List<FlipFitGymCenter> approvedGymCenters = adminService.getApprovedGymCenters();
                    printGymCentres(approvedGymCenters);
                    break;
                case 4:
                    List<FlipFitGymCenter> unapprovedGymCenters = adminService.getUnapprovedGymCenters();
                    printGymCentres(unapprovedGymCenters);
                    if (!unapprovedGymCenters.isEmpty())
                        handleGymCenterApprovalRequests();
                    break;
                case 5:
                    List<FlipFitGymOwner> allGymOwners = gymOwnerService.viewAllGymOwners();
                    printOwnerList(allGymOwners);
                    break;
                case 6:
                    List<FlipFitGymCenter> allGymCenters = gymCenterService.viewAllGymCenters();
                    printGymCentres(allGymCenters);
                    break;
                case 7:
                    return;
            }
        }
    }
}
