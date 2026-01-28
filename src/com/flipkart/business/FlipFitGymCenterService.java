package com.flipkart.business;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.dao.FlipFitGymCenterDAO;
import java.util.List;

/**
 * Service class for Gym Center operations.
 * 
 * @author gamma-group
 */
public class FlipFitGymCenterService implements FlipFitGymCenterInterface {
    private static FlipFitGymCenterDAO flipFitGymCenterDAO = new FlipFitGymCenterDAO();
    private static FlipFitScheduleInterface scheduleService = new FlipFitScheduleService();

    /**
     * View all gym centers.
     * 
     * @return List of all gym centers
     */
    public List<FlipFitGymCenter> viewAllGymCenters() {
        return flipFitGymCenterDAO.viewAllGymCenters();
    }

    /**
     * Get all centers by owner ID.
     * 
     * @param gymOwnerId Gym Owner ID
     * @return List of gym centers
     */
    public List<FlipFitGymCenter> getAllCentresByOwmerId(String gymOwnerId) {
        return flipFitGymCenterDAO.getAllCentresByOwmerId(gymOwnerId);
    }

    /**
     * Get centers by city.
     * 
     * @param city City
     * @return List of gym centers
     */
    public List<FlipFitGymCenter> getCentresByCity(String city) {
        return flipFitGymCenterDAO.getGymCentreListByCity(city);
    }

    /**
     * Get available slots by center and date.
     * 
     * @param centreID Center ID
     * @param date     Date
     * @return List of available slots
     */
    public List<FlipFitSlot> getAvailableSlotsByCentreAndDate(String centreID, java.util.Date date) {
        return scheduleService.getAllAvailableSlotsByDate(centreID, date);
    }

    /**
     * Add a center.
     * 
     * @param gymCentre Gym center object
     */
    public void addCenter(FlipFitGymCenter gymCentre) {
        // takes gymCenter details
        flipFitGymCenterDAO.addGymCentre(gymCentre);

        // Auto-generate slots
        java.util.List<FlipFitSlot> slots = new java.util.ArrayList<>();
        int openTime = gymCentre.getOpenTime();
        int noOfSlots = gymCentre.getNoOfSlots();

        java.util.stream.IntStream.range(0, noOfSlots).forEach(i -> {
            // Generate UUID for slot
            String slotId = java.util.UUID.randomUUID().toString();
            // Calculate time: openTime + i hours
            java.time.LocalTime time = java.time.LocalTime.of(openTime + i, 0); // Assuming 1 hour slots

            FlipFitSlot slot = new FlipFitSlot(slotId, gymCentre.getGymCenterId(), time);
            slots.add(slot);
        });

        FlipFitSlotInterface slotService = new FlipFitSlotService();
        slotService.addSlotsForGym(gymCentre.getGymCenterId(), slots);
    }

    /**
     * Request gym center approval.
     * 
     * @param gymCentreId Gym Center ID
     */
    public void requestGymCentreApproval(String gymCentreId) {
        flipFitGymCenterDAO.sendCentreApprovalRequest(gymCentreId);
    }

    /**
     * Get gym center by ID.
     * 
     * @param centreID Center ID
     * @return Gym Center object
     */
    public FlipFitGymCenter getGymCentreById(String centreID) {
        FlipFitGymCenter gymCenter = flipFitGymCenterDAO.getGymCentreByCentreId(centreID);
        return gymCenter;
    }
}
