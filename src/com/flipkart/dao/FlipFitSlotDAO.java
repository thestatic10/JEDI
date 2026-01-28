package com.flipkart.dao;

import com.flipkart.bean.FlipFitSlot;

import com.flipkart.constants.SQLConstants;
import com.flipkart.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

/**
 * Data Access Object (DAO) implementation for Slot operations.
 * 
 * @author gamma-group
 */
public class FlipFitSlotDAO implements FlipFitSlotDAOInterface {

    public FlipFitSlotDAO() {
    }

    /**
     * Get slot list.
     * 
     * @return List of slots
     */
    public List<FlipFitSlot> getSlotList() {
        List<FlipFitSlot> slotList = new ArrayList<>();
        try {
            Connection conn = DatabaseConnector.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.FETCH_ALL_SLOTS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String slotId = rs.getString("slotId");
                String centreId = rs.getString("centreId");
                LocalTime time = rs.getTime("time").toLocalTime();

                slotList.add(new FlipFitSlot(slotId, centreId, time));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slotList;
    }

    /**
     * Get slot by center ID.
     * 
     * @param gymCentreId Gym Center ID
     * @return List of slots
     */
    public List<FlipFitSlot> getSlotByCentreId(String gymCentreId) {
        List<FlipFitSlot> slotList = new ArrayList<>();
        try {
            Connection conn = DatabaseConnector.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.FETCH_SLOT_BY_CENTRE);
            ps.setString(1, gymCentreId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String slotId = rs.getString("slotId");
                String centreId = rs.getString("centreId");
                LocalTime time = rs.getTime("time").toLocalTime();

                slotList.add(new FlipFitSlot(slotId, centreId, time));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slotList;
    }

    /**
     * Add slot.
     * 
     * @param slot Slot object
     */
    public void addSlot(FlipFitSlot slot) {
        try {
            Connection conn = DatabaseConnector.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.ADD_SLOT);
            ps.setString(1, slot.getSlotId());
            ps.setString(2, slot.getCenterID());
            ps.setTime(3, java.sql.Time.valueOf(slot.getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get slot by ID.
     * 
     * @param slotID Slot ID
     * @return Slot object
     */
    public FlipFitSlot getSlotById(String slotID) {
        FlipFitSlot slot = null;
        try {
            Connection conn = DatabaseConnector.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.FETCH_SLOT_BY_ID);
            ps.setString(1, slotID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String centreId = rs.getString("centreId");
                LocalTime time = rs.getTime("time").toLocalTime();
                slot = new FlipFitSlot(slotID, centreId, time);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slot;
    }

    /**
     * Get slot by ID and Center ID.
     * 
     * @param slotID   Slot ID
     * @param centreID Center ID
     * @return Slot object
     */
    public FlipFitSlot getSlotByIdandCentreId(String slotID, String centreID) {
        FlipFitSlot slot = null;
        try {
            Connection conn = DatabaseConnector.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.FETCH_SLOT_BY_ID_AND_CENTRE_ID);
            ps.setString(1, slotID);
            ps.setString(2, centreID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalTime time = rs.getTime("time").toLocalTime();
                slot = new FlipFitSlot(slotID, centreID, time);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slot;
    }
}
