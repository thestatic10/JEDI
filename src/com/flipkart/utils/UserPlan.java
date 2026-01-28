package com.flipkart.utils;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a user plan.
 * 
 * @author gamma-group
 */
public class UserPlan {
    /**
     * Get slot ID.
     * 
     * @return Slot ID
     */
    public String getSlotId() {
        return slotId;
    }

    /**
     * Set slot ID.
     * 
     * @param slotId Slot ID
     */
    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    /**
     * Get center ID.
     * 
     * @return Center ID
     */
    public String getCentreID() {
        return centreID;
    }

    /**
     * Set center ID.
     * 
     * @param centreID Center ID
     */
    public void setCentreID(String centreID) {
        this.centreID = centreID;
    }

    /**
     * Get time.
     * 
     * @return Time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Set time.
     * 
     * @param time Time
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Get schedule ID.
     * 
     * @return Schedule ID
     */
    public String getScheduleID() {
        return scheduleID;
    }

    /**
     * Set schedule ID.
     * 
     * @param scheduleID Schedule ID
     */
    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    /**
     * Get date.
     * 
     * @return Date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Set date.
     * 
     * @param date Date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    private String slotId;
    private String centreID;
    private LocalTime time;
    private String scheduleID;
    private LocalDate date;

    /**
     * Constructor.
     * 
     * @param slotId     Slot ID
     * @param centreID   Center ID
     * @param time       Time
     * @param scheduleID Schedule ID
     * @param date       Date
     */
    public UserPlan(String slotId, String centreID, LocalTime time, String scheduleID, LocalDate date) {
        this.time = time;
        this.slotId = slotId;
        this.date = date;
        this.scheduleID = scheduleID;
        this.centreID = centreID;
    }
}
