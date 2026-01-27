package com.flipkart.business;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.dao.FlipFitGymCenterDAO;
import java.util.List;

public class FlipFitGymCenterService implements FlipFitGymCenterInterface {
    private static FlipFitGymCenterDAO flipFitGymCenterDAO = new FlipFitGymCenterDAO();
    private static FlipFitScheduleInterface scheduleService = new FlipFitScheduleService();

    public List<FlipFitGymCenter> viewAllGymCenters() {
        return flipFitGymCenterDAO.viewAllGymCenters();
    }

    public List<FlipFitGymCenter> getAllCentresByOwmerId(String gymOwnerId) {
        return flipFitGymCenterDAO.getAllCentresByOwmerId(gymOwnerId);
    }

    public List<FlipFitGymCenter> getCentresByCity(String city){
        return flipFitGymCenterDAO.getGymCentreListByCity(city);
    }

    public List<FlipFitSlot> getAvailableSlotsByCentreAndDate(String centreID, java.util.Date date) {
        return scheduleService.getAllAvailableSlotsByDate(centreID, date);
    }

    public void addCenter(FlipFitGymCenter gymCentre) {
        //takes gymCenter details
        flipFitGymCenterDAO.addGymCentre(gymCentre);
    }

    public void requestGymCentreApproval(String gymCentreId){
        flipFitGymCenterDAO.sendCentreApprovalRequest(gymCentreId);
    }

    public FlipFitGymCenter getGymCentreById(String centreID) {
        FlipFitGymCenter gymCenter = flipFitGymCenterDAO.getGymCentreByCentreId(centreID);
        return gymCenter;
    }
}
