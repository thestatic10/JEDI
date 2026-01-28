package com.flipkart.business;

import com.flipkart.dao.FlipFitGymOwnerDAO;
import com.flipkart.bean.FlipFitGymOwner;

import java.util.List;

/**
 * Service class for Gym Owner operations.
 * 
 * @author gamma-group
 */
public class FlipFitGymOwnerService implements FlipFitGymOwnerInterface {
    private static final FlipFitGymOwnerDAO FLIP_FIT_GYM_OWNER_DAO = new FlipFitGymOwnerDAO();

    /**
     * Request gym owner approval.
     * 
     * @param gymOwnerId Gym Owner ID
     */
    public void requestGymOwnerApproval(String gymOwnerId) {
        FLIP_FIT_GYM_OWNER_DAO.sendOwnerApprovalRequest(gymOwnerId);
    }

    /**
     * View all gym owners.
     * 
     * @return List of all gym owners
     */
    public List<FlipFitGymOwner> viewAllGymOwners() {
        return FLIP_FIT_GYM_OWNER_DAO.getGymOwnerList();
    }

    /**
     * Login gym owner.
     * 
     * @param username Username
     * @param password Password
     * @return True if login successful, false otherwise
     */
    public boolean loginGymOwner(String username, String password) {
        return FLIP_FIT_GYM_OWNER_DAO.loginGymOwner(username, password);
    }

    /**
     * Register gym owner.
     * 
     * @param userId     User ID
     * @param userName   Username
     * @param password   Password
     * @param email      Email
     * @param panNumber  PAN number
     * @param cardNumber Card number
     */
    public void registerGymOwner(String userId, String userName, String password, String email, String panNumber,
            String cardNumber) {
        FLIP_FIT_GYM_OWNER_DAO
                .registerGymOwner(new FlipFitGymOwner(userId, userName, email, password, panNumber, cardNumber));
    }
}