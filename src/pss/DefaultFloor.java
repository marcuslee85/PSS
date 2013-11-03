/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pss;

/**
 *
 * @author nine_ball
 */
public class DefaultFloor {
    int userid;
    int floor;
    int priority;

    public DefaultFloor(int userid, int floor, int priority) {
        this.userid = userid;
        this.floor = floor;
        this.priority = priority;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    
}
