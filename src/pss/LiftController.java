/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pss;

/**
 *
 * @author marcus
 */
class LiftController {

    Lift lift1, lift2, lift3, lift4, lift5;

    public LiftController() {
        this.lift1 = new Lift(1);
        this.lift2 = new Lift(2);
        this.lift3 = new Lift(3);
        this.lift4 = new Lift(4);
        this.lift5 = new Lift(5);
    }

    public Lift getLift1() {
        return lift1;
    }

    public void setLift1(Lift lift1) {
        this.lift1 = lift1;
    }

    public Lift getLift2() {
        return lift2;
    }

    public void setLift2(Lift lift2) {
        this.lift2 = lift2;
    }

    public Lift getLift3() {
        return lift3;
    }

    public void setLift3(Lift lift3) {
        this.lift3 = lift3;
    }

    public Lift getLift4() {
        return lift4;
    }

    public void setLift4(Lift lift4) {
        this.lift4 = lift4;
    }

    public Lift getLift5() {
        return lift5;
    }

    public void setLift5(Lift lift5) {
        this.lift5 = lift5;
    }

    public boolean pickUp(Person p1) {
        int liftNumber=1;
        int numberOfPplInQueue=10;
        //black ops and management only
        //all lifts on lockdown
        if(p1.type==1 || p1.type==2 || p1.type==3){
            if(lift1.personsToPickUp.size()<numberOfPplInQueue){
                numberOfPplInQueue=lift1.personsToPickUp.size();
            }
            if(lift2.personsToPickUp.size()<numberOfPplInQueue){
                numberOfPplInQueue=lift2.personsToPickUp.size();
                liftNumber=2;
            }
            if(lift3.personsToPickUp.size()<numberOfPplInQueue){
                numberOfPplInQueue=lift3.personsToPickUp.size();
                liftNumber=3;
            }
            if(lift4.personsToPickUp.size()<numberOfPplInQueue){
                numberOfPplInQueue=lift4.personsToPickUp.size();
                liftNumber=4;
            }
            if(lift5.personsToPickUp.size()<numberOfPplInQueue){
                liftNumber=5;
            }
            
            if(liftNumber==1){
                if (lift1.addPerson(p1)) {
                    return true;
                }
            }else if(liftNumber==2){
                if (lift2.addPerson(p1)) {
                    return true;
                }
            }else if(liftNumber==3){
                if (lift3.addPerson(p1)) {
                    return true;
                }
            }else if(liftNumber==4){
                if (lift4.addPerson(p1)) {
                    return true;
                }
            }else{
                if (lift5.addPerson(p1)) {
                    return true;
                }
            }
        }
        
        //vvip
        //check which lift first to clear
        //add vip to queue
        //lift should not add anymore persons until vip is out of lift queue and lift persons
        
        
        
        
        
        
        
        
        
        if (p1.getArrivalFloor() < p1.getDestinationFloor()) {
            //going up
            if (lift1.direction.equalsIgnoreCase("up") && lift1.floor < p1.getArrivalFloor()) {
                if (lift1.addPerson(p1)) {
                    return true;
                }
            } else if (lift2.direction.equalsIgnoreCase("up") && lift2.floor < p1.getArrivalFloor()) {
                if (lift2.addPerson(p1)) {
                    return true;
                }
            } else if (lift3.direction.equalsIgnoreCase("up") && lift3.floor < p1.getArrivalFloor()) {
                if (lift3.addPerson(p1)) {
                    return true;
                }
            } else if (lift4.direction.equalsIgnoreCase("up") && lift4.floor < p1.getArrivalFloor()) {
                if (lift4.addPerson(p1)) {
                    return true;
                }
            } else if (lift5.direction.equalsIgnoreCase("up") && lift5.floor < p1.getArrivalFloor()) {
                if (lift5.addPerson(p1)) {
                    return true;
                }
            }
        }
        if (p1.getArrivalFloor() > p1.getDestinationFloor()) {
            if (lift1.direction.equalsIgnoreCase("down") && lift1.floor > p1.getArrivalFloor()) {
                if (lift1.addPerson(p1)) {
                    return true;
                }
            } else if (lift2.direction.equalsIgnoreCase("down") && lift2.floor > p1.getArrivalFloor()) {
                if (lift2.addPerson(p1)) {
                    return true;
                }
            } else if (lift3.direction.equalsIgnoreCase("down") && lift3.floor > p1.getArrivalFloor()) {
                if (lift3.addPerson(p1)) {
                    return true;
                }
            } else if (lift4.direction.equalsIgnoreCase("down") && lift4.floor > p1.getArrivalFloor()) {
                if (lift4.addPerson(p1)) {
                    return true;
                }
            } else if (lift5.direction.equalsIgnoreCase("down") && lift5.floor > p1.getArrivalFloor()) {
                if (lift5.addPerson(p1)) {
                    return true;
                }
            }
        }

        if ((lift1.direction.equalsIgnoreCase("notMoving")) ) {
            if (lift1.addPerson(p1)) {
                return true;
            }
        }
        if ((lift2.direction.equalsIgnoreCase("notMoving"))) {
            if (lift2.addPerson(p1)) {
                return true;
            }
        }
        if ((lift3.direction.equalsIgnoreCase("notMoving"))) {
            if (lift3.addPerson(p1)) {
                return true;
            }
        }
        if ((lift4.direction.equalsIgnoreCase("notMoving"))) {
            if (lift4.addPerson(p1)) {
                return true;
            }
        }
        if ((lift5.direction.equalsIgnoreCase("notMoving"))) {
            if (lift5.addPerson(p1)) {
                return true;
            }
        }
    

    return false; //all lifts full or in wrong direction
    }
    
    public int liftGetNextStep(int i1){
        if(i1==1){
            return lift1.nextStep();
        }if(i1==2){
            return lift2.nextStep();
        }if(i1==3){
            return lift3.nextStep();
        }if(i1==4){
            return lift4.nextStep();
        }if(i1==5){
            return lift5.nextStep();
        }
        return 0; //fatal error
    }

    public int liftGetNumberOfPersons(int i1) {
        if(i1==1){
            return lift1.persons.size();
        }if(i1==2){
            return lift2.persons.size();
        }if(i1==3){
            return lift3.persons.size();
        }if(i1==4){
            return lift4.persons.size();
        }if(i1==5){
            return lift5.persons.size();
        }
        return 0; //fatal error
    }
}
