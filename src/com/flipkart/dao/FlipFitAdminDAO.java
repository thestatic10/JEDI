package com.flipkart.dao;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymOwner;

import java.util.List;

public class FlipFitAdminDAO implements FlipFitAdminDAOInterface {

    private static FlipFitGymOwnerDAO flipFitGymOwnerDAO = new FlipFitGymOwnerDAO();
    private static FlipFitGymCenterDAO gymCentreDAO = new FlipFitGymCenterDAO();


    public List<FlipFitGymOwner> getPendingGymOwners() {
        return flipFitGymOwnerDAO.getPendingGymOwnerList();
    }

    public void validateGymOwner(String gymOwnerId, boolean isApproved) {
        flipFitGymOwnerDAO.validateGymOwner(gymOwnerId, isApproved);
    }

    public void validateGymCentre(String gymCentreId, boolean isApproved) {
        gymCentreDAO.validateGymCentre(gymCentreId, isApproved);
    }

    public List<FlipFitGymCenter> getPendingGymCentres() {
        return gymCentreDAO.getPendingGymCentreList();
    }

}
