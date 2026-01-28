package com.flipkart.business;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.dao.FlipFitAdminDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for Admin operations.
 * 
 * @author gamma-group
 */
public class FlipFitAdminService implements FlipFitAdminInterface {

    FlipFitAdminDAO flipFitAdminDAO = new FlipFitAdminDAO();

    private List<FlipFitGymOwner> pendinGymOwnerList = new ArrayList<>();
    private List<FlipFitGymCenter> pendinGymCentreList = new ArrayList<>();

    /**
     * Approves or rejects a gym center.
     * 
     * @param gymCentreId ID of the gym center
     * @param isApproved  Approval status
     */
    public void approveGymCenter(String gymCentreId, boolean isApproved) {
        flipFitAdminDAO.validateGymCentre(gymCentreId, isApproved);
    }

    /**
     * Approves or rejects a gym owner.
     * 
     * @param gymOwnerId ID of the gym owner
     * @param isApproved Approval status
     */
    public void approveGymOwner(String gymOwnerId, boolean isApproved) {
        flipFitAdminDAO.validateGymOwner(gymOwnerId, isApproved);
    }

    /**
     * View pending gym centers.
     * 
     * @return List of pending gym centers
     */
    public List<FlipFitGymCenter> viewPendingGymCentres() {
        pendinGymCentreList = flipFitAdminDAO.getPendingGymCentres();
        return pendinGymCentreList;
    }

    /**
     * View pending gym owners.
     * 
     * @return List of pending gym owners
     */
    public List<FlipFitGymOwner> viewPendingGymOwners() {
        System.out.println("Viewing pending Gym Owner Approvals: ");
        pendinGymOwnerList = flipFitAdminDAO.getPendingGymOwners();
        return pendinGymOwnerList;
    }

    /**
     * Validates if the user is valid.
     * 
     * @param userName Username of the admin
     * @param password Password of the admin
     * @return True if valid, false otherwise
     */
    public boolean isUserValid(String userName, String password) {
        return flipFitAdminDAO.isUserValid(userName, password);
    }

    /**
     * Registers a new admin.
     * 
     * @param userName Username of the admin
     * @param password Password of the admin
     * @param email    Email of the admin
     */
    public void registerAdmin(String userName, String password, String email) {
        flipFitAdminDAO.registerAdmin(userName, password, email);
    }

    /**
     * Get list of approved gym owners.
     * 
     * @return List of approved gym owners
     */
    public List<FlipFitGymOwner> getApprovedGymOwners() {
        return flipFitAdminDAO.getAllGymOwners().stream()
                .filter(FlipFitGymOwner::isApproved)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Get list of unapproved gym owners.
     * 
     * @return List of unapproved gym owners
     */
    public List<FlipFitGymOwner> getUnapprovedGymOwners() {
        return flipFitAdminDAO.getAllGymOwners().stream()
                .filter(owner -> !owner.isApproved())
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Get list of approved gym centers.
     * 
     * @return List of approved gym centers
     */
    public List<FlipFitGymCenter> getApprovedGymCenters() {
        return flipFitAdminDAO.getAllGymCenters().stream()
                .filter(FlipFitGymCenter::isApproved)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Get list of unapproved gym centers.
     * 
     * @return List of unapproved gym centers
     */
    public List<FlipFitGymCenter> getUnapprovedGymCenters() {
        return flipFitAdminDAO.getAllGymCenters().stream()
                .filter(center -> !center.isApproved())
                .collect(java.util.stream.Collectors.toList());
    }

}
