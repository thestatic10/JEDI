package com.flipkart.dao;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymOwner;

import java.util.List;

/**
 * Data Access Object (DAO) interface for handling administrative tasks in FlipFit system.
 * This interface defines methods related to gym owners and gym centers approval/validation.
 */
public interface FlipFitAdminDAOInterface {

    /**
     * Retrieves a list of pending gym owners awaiting approval.
     * @return List of pending gym owners
     */
    List<FlipFitGymOwner> getPendingGymOwners();

    /**
     * Validates (approves or rejects) a gym owner based on the given ID.
     * @param gymOwnerId ID of the gym owner to validate
     * @param isApproved True to approve, false to reject
     */
    void validateGymOwner(String gymOwnerId, boolean isApproved);

    /**
     * Validates (approves or rejects) a gym center based on the given ID.
     * @param gymCentreId ID of the gym center to validate
     * @param isApproved True to approve, false to reject
     */
    void validateGymCentre(String gymCentreId, boolean isApproved);

    /**
     * Retrieves a list of pending gym centers awaiting approval.
     * @return List of pending gym centers
     */
    List<FlipFitGymCenter> getPendingGymCentres();
}
