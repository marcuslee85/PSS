package pss;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nine_ball
 */
public class Schedule {
    int userid;
    int startTiming;
    int endTiming;
    int floor;
    
    public Schedule (int userid, int startTiming, int endTiming, int floor) {
        
        this.userid = userid;
        this.startTiming = startTiming;
        this.endTiming = endTiming;
        this.floor = floor;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getStartTiming() {
        return startTiming;
    }

    public void setStartTiming(int startTiming) {
        this.startTiming = startTiming;
    }

    public int getEndTiming() {
        return endTiming;
    }

    public void setEndTiming(int endTiming) {
        this.endTiming = endTiming;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
    
    
}
