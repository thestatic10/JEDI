package com.flipkart.dao;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.constants.SQLConstants;
import com.flipkart.utils.DatabaseConnector;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.flipkart.constants.SQLConstants.*;

/**
 * Data Access Object (DAO) implementation for Gym Center operations.
 * 
 * @author gamma-group
 */
public class FlipFitGymCenterDAO implements FlipFitGymCentreDAOInterface {

    private Connection conn = null;
    private PreparedStatement statement = null;

    /**
     * Constructor.
     */
    public FlipFitGymCenterDAO() {
    }

    /**
     * View all gym centers.
     * 
     * @return List of all gym centers
     */
    public List<FlipFitGymCenter> viewAllGymCenters() {
        List<FlipFitGymCenter> allGymCentres = new ArrayList<>();
        try {
            conn = DatabaseConnector.connect();
            statement = conn.prepareStatement(FETCH_ALL_GYM_CENTRES);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FlipFitGymCenter gymCentre = new FlipFitGymCenter(
                        rs.getString("centreId"),
                        rs.getString("ownerId"),
                        rs.getString("centreName"),
                        rs.getString("gstin"),
                        rs.getString("city"),
                        rs.getInt("capacity"),
                        rs.getInt("price"),
                        rs.getBoolean("isApproved"),
                        rs.getInt("noOfSlots"),
                        rs.getInt("openTime"));
                gymCentre.setApproved(rs.getBoolean("isApproved"));
                allGymCentres.add(gymCentre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allGymCentres;
    }

    @Override
    public List<FlipFitGymCenter> getAllCentresByOwnerId(String gymOwnerId) {
        return List.of();
    }

    /**
     * Get all centers by owner ID.
     * 
     * @param gymOwnerId Gym Owner ID
     * @return List of gym centers
     */
    public List<FlipFitGymCenter> getAllCentresByOwmerId(String gymOwnerId) {

        List<FlipFitGymCenter> allGymCentres = new ArrayList<>();
        try {
            conn = DatabaseConnector.connect();
            statement = conn.prepareStatement(FETCH_GYM_CENTRES_BY_OWNER_ID);
            statement.setString(1, gymOwnerId);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FlipFitGymCenter gymCentre = new FlipFitGymCenter(
                        rs.getString("centreId"),
                        rs.getString("ownerId"),
                        rs.getString("centreName"),
                        rs.getString("gstin"),
                        rs.getString("city"),
                        rs.getInt("capacity"),
                        rs.getInt("price"),
                        rs.getBoolean("isApproved"),
                        rs.getInt("noOfSlots"),
                        rs.getInt("openTime"));
                gymCentre.setApproved(rs.getBoolean("isApproved"));
                allGymCentres.add(gymCentre);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allGymCentres;
    }

    /**
     * Get gym center by ID.
     * 
     * @param gymCentreId Gym Center ID
     * @return Gym Center object
     */
    public FlipFitGymCenter getGymCentreByCentreId(String gymCentreId) {
        FlipFitGymCenter gymCentre = new FlipFitGymCenter();
        try {
            Connection conn = DatabaseConnector.connect();
            PreparedStatement stmt = conn.prepareStatement(FETCH_GYM_CENTRE_BY_ID);
            stmt.setString(1, gymCentreId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            gymCentre.setGymCenterId(rs.getString("centreId"));
            gymCentre.setOwnerID(rs.getString("ownerId"));
            gymCentre.setGymCenterName(rs.getString("centreName"));
            gymCentre.setGstin(rs.getString("gstin"));
            gymCentre.setCity(rs.getString("city"));
            gymCentre.setCapacity(rs.getInt("capacity"));
            gymCentre.setPrice(rs.getInt("price"));
            gymCentre.setNoOfSlots(rs.getInt("noOfSlots"));
            gymCentre.setOpenTime(rs.getInt("openTime"));
            gymCentre.setApproved(rs.getBoolean("isApproved"));
            stmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }

        return gymCentre;

    }

    /**
     * Add gym center.
     * 
     * @param centre Gym Center object
     */
    public void addGymCentre(FlipFitGymCenter centre) {
        // call to db api
        try {
            conn = DatabaseConnector.connect();
            System.out.println("Adding gym centre....");

            // INSERT INTO FlipFit.GymCentre (centreId, ownerId, centreName, gstin, city,
            // capacity, price, isApproved)
            statement = conn.prepareStatement(SQLConstants.ADD_GYM_CENTRE_QUERY);
            statement.setString(1, centre.getGymCenterId());
            statement.setString(2, centre.getOwnerID());
            statement.setString(3, centre.getGymCenterName());
            statement.setString(4, centre.getGstin());
            statement.setString(5, centre.getCity());
            statement.setInt(6, centre.getCapacity());
            statement.setInt(7, centre.getPrice());
            statement.setBoolean(8, centre.isApproved());
            statement.setInt(9, centre.getNoOfSlots());
            statement.setInt(10, centre.getOpenTime());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get pending gym center list.
     * 
     * @return List of pending gym centers
     */
    public List<FlipFitGymCenter> getPendingGymCentreList() {
        List<FlipFitGymCenter> pendingList = new ArrayList<>();
        try {
            conn = DatabaseConnector.connect();
            System.out.println("Fetching gym centres..");

            statement = conn.prepareStatement(SQLConstants.FETCH_ALL_PENDING_GYM_CENTRES_QUERY);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FlipFitGymCenter gymCentre = new FlipFitGymCenter(
                        rs.getString("centreId"),
                        rs.getString("ownerId"),
                        rs.getString("centreName"),
                        rs.getString("gstin"),
                        rs.getString("city"),
                        rs.getInt("capacity"),
                        rs.getInt("price"),
                        rs.getBoolean("isApproved"),
                        rs.getInt("noOfSlots"),
                        rs.getInt("openTime"));
                gymCentre.setApproved(rs.getBoolean("isApproved"));
                pendingList.add(gymCentre);
            }
            // conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pendingList;
    }

    /**
     * Validate gym center.
     * 
     * @param gymCentreId Gym Center ID
     * @param isApproved  Approval status
     */
    public void validateGymCentre(String gymCentreId, boolean isApproved) {
        // System.out.println("IN VALIDATE GYM CENTRE");
        try {
            conn = DatabaseConnector.connect();
            System.out.println("Fetching gyms centres..");

            statement = conn.prepareStatement(SQLConstants.SQL_APPROVE_GYM_CENTRE_BY_ID_QUERY);
            statement.setBoolean(1, isApproved);
            statement.setString(2, gymCentreId);
            statement.executeUpdate();
            // System.out.println("The gym centre has been approved!");
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    /**
     * Send centre approval request.
     * 
     * @param gymCentreId Gym Center ID
     */
    public void sendCentreApprovalRequest(String gymCentreId) {
        try {
            conn = DatabaseConnector.connect();
            System.out.println("Sending gym centre approval request..");
            // SQL_APPROVE_GYM_CENTRE_BY_ID_QUERY="Update GymCentre Set isApproved=? WHERE
            // centreId=?";
            statement = conn.prepareStatement(SQLConstants.SQL_APPROVE_GYM_CENTRE_BY_ID_QUERY);
            statement.setInt(1, 0);
            statement.setString(2, gymCentreId);
            statement.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get gym center list by city.
     * 
     * @param city City
     * @return List of gym centers
     */
    public List<FlipFitGymCenter> getGymCentreListByCity(String city) {
        List<FlipFitGymCenter> allCentreByCity = new ArrayList<>();
        try {
            conn = DatabaseConnector.connect();
            System.out.println("Fetching gyms centres by City..");
            statement = conn.prepareStatement(SQLConstants.FETCH_GYM_CENTRES_BY_CITY);
            statement.setString(1, city);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FlipFitGymCenter gymCentre = new FlipFitGymCenter(
                        rs.getString("centreId"),
                        rs.getString("ownerId"),
                        rs.getString("centreName"),
                        rs.getString("gstin"),
                        rs.getString("city"),
                        rs.getInt("capacity"),
                        rs.getInt("price"),
                        rs.getBoolean("isApproved"),
                        rs.getInt("noOfSlots"),
                        rs.getInt("openTime"));
                allCentreByCity.add(gymCentre);
            }
            // System.out.println("The gym centre has been approved!");
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
        return allCentreByCity;
    }
}