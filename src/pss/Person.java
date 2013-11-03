package pss;

import java.util.Random;

/**
 *
 * @author marcus
 */
public class Person {

    int userid;
    int type = 0;
    int timing; //6 digits hhmmss
    int arrivalFloor;
    int destinationFloor;

    public Person(int type) {
        Random r = new Random();
        this.type = type;
        //this.timing = r.nextInt(23) * 10000 + r.nextInt(59) * 100 + r.nextInt(59); //hhmmss
        this.timing = r.nextInt(59) * 100 + r.nextInt(59); //@todo switch back to other one hhmmss
        this.timing = r.nextInt(23) * 10000 + r.nextInt(59) * 100 + r.nextInt(59); //hhmmss
        this.destinationFloor = r.nextInt(15) - 2; //@todo include carparks
        if (this.destinationFloor == 0) {
            this.destinationFloor = 1;
        }
        this.arrivalFloor = r.nextInt(15) - 2; //@todo include carparks
        if (this.arrivalFloor == 0) {
            this.arrivalFloor = 1;
        }
        while (this.destinationFloor == this.arrivalFloor) {
            this.destinationFloor = r.nextInt(15) - 2;
            if (this.destinationFloor == 0) {
                this.destinationFloor = 1;
            }
        }
    }
    
    public Person(int userid, int type, int timing, int arrivalFloor, int destinationFloor) {
        this.userid = userid;
        this.type = type;
        this.timing = timing;
        this.arrivalFloor = arrivalFloor;
        this.destinationFloor = destinationFloor;
    }

    public int getType() {
        return type;
    }

    public String getTypeName() {
        if (type == 1) {
            return "Black Ops";
        } else if (type == 2) {
            return "VVIP";
        } else if (type == 3) {
            return "Management";
        } else if (type == 4) {
            return "Black Ops Employee";
        } else if (type == 5) {
            return "Employee";
        } else if (type == 6) {
            return "Visitor";
        } else if (type == 7) {
            return "Maintenance";
        } else if (type == 8) {
            return "Cleaner";
        } else {
            return null; //somewhere u F up
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

    public boolean sort(Person pt2) {
        if (timing < pt2.timing) {
            return true;
        } else {
            return false;
        }
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public int getArrivalFloor() {
        return arrivalFloor;
    }

    public void setArrivalFloor(int arrivalFloor) {
        this.arrivalFloor = arrivalFloor;
    }

}
