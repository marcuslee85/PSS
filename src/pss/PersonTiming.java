/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pss;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author marcus
 */
public class PersonTiming {
    int type = 0;
    int timing;
    int destinationFloor;

    public PersonTiming(int type) {
        Random r = new Random();
        this.type = type;
        this.timing = r.nextInt(2359);
        this.destinationFloor = r.nextInt(11)+1;
    }

    public int getType() {
        return type;
    }
    
    public String getTypeName(){
        if(type==1){
            return "Black Ops";
        }else if(type==2){
            return "VVIP";
        }else if(type==3){
            return "Management";
        }else if(type==4){
            return "Black Ops Employee";
        }else if(type==5){
            return "Employee";
        }else if(type==6){
            return "Visitor";
        }else if(type==7){
            return "Maintenance";
        }else{
            return "Cleaner";
        }
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTiming() {
        return timing;
    }

    public void setTiming(int timing) {
        this.timing = timing;
    }

    public boolean sort(PersonTiming pt2){
        if(timing<pt2.timing){
            return true;
        }else{
            return false;
        }
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }
    
    
    
}
