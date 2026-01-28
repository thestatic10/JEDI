package com.flipkart.business;

import com.flipkart.dao.FlipFitSlotDAO;
import com.flipkart.bean.FlipFitSlot;

import java.util.List;

import static java.nio.file.Paths.get;

/**
 * Service class for Slot operations.
 * 
 * @author gamma-group
 */
public class FlipFitSlotService implements FlipFitSlotInterface {
    private static final FlipFitSlotDAO FLIP_FIT_SLOT_DAO = new FlipFitSlotDAO();

    /**
     * Get all slots by center ID.
     * 
     * @param centreID Center ID
     * @return List of slots
     */
    public List<FlipFitSlot> getAllSlotsByCentre(String centreID) {
        return FLIP_FIT_SLOT_DAO.getSlotByCentreId(centreID);
    }

    /**
     * Get slot by ID.
     * 
     * @param slotID Slot ID
     * @return Slot object
     */
    public FlipFitSlot getSlotByID(String slotID) {
        return FLIP_FIT_SLOT_DAO.getSlotById(slotID);
    }

    /**
     * Get slot by ID and Center ID.
     * 
     * @param slotID   Slot ID
     * @param centreId Center ID
     * @return Slot object
     */
    public FlipFitSlot getSlotByIDCentreId(String slotID, String centreId) {
        return FLIP_FIT_SLOT_DAO.getSlotByIdandCentreId(slotID, centreId);
    }

    /**
     * Get all slots list.
     * 
     * @return List of all slots
     */
    public List<FlipFitSlot> getSlotList() {
        return FLIP_FIT_SLOT_DAO.getSlotList();
    }

    /**
     * Add slots for gym.
     * 
     * @param gymCentreId Gym Center ID
     * @param slotList    List of slots
     */
    public void addSlotsForGym(String gymCentreId, List<FlipFitSlot> slotList) {
        System.out.println("Adding all slots to gym: " + gymCentreId);
        for (FlipFitSlot slot : slotList) {
            slot.setCenterID(gymCentreId);
            FLIP_FIT_SLOT_DAO.addSlot(slot);
        }
    }

    /**
     * Check if slot is valid.
     * 
     * @param slotID   Slot ID
     * @param centreId Center ID
     * @return True if valid
     */
    public boolean isSlotValid(String slotID, String centreId) {
        get(slotID, centreId);
        return true;
    }
}