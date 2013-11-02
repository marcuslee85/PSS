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
    Lift lift1,lift2,lift3,lift4,lift5;

    public LiftController() {
        this.lift1 = new Lift(1);
        this.lift2 = new Lift(2);
        this.lift3 = new Lift(3);
        this.lift4 = new Lift(4);
        this.lift5 = new Lift(5);
    }
    
    public boolean pickUp(Person p1){
        if(p1.getArrivalFloor() < p1.getDestinationFloor()){
            //going up
            if(lift1.direction.equalsIgnoreCase("up")){
                if(lift1.floor < p1.getArrivalFloor()){
                    if(lift1.addPerson(p1)){
                        return true;
                    }
                }else if(lift2.floor < p1.getArrivalFloor()){
                    if(lift2.addPerson(p1)){
                        return true;
                    }
                }else if(lift3.floor < p1.getArrivalFloor()){
                    if(lift3.addPerson(p1)){
                        return true;
                    }
                }else if(lift4.floor < p1.getArrivalFloor()){
                    if(lift4.addPerson(p1)){
                        return true;
                    }
                }else if(lift5.floor < p1.getArrivalFloor()){
                    if(lift5.addPerson(p1)){
                        return true;
                    }
                }else{
                    if((lift1.direction.equalsIgnoreCase("notMoving"))){
                        if(lift1.addPerson(p1)){
                            return true;
                        }
                    }if((lift2.direction.equalsIgnoreCase("notMoving"))){
                        if(lift2.addPerson(p1)){
                            return true;
                        }
                    }if((lift3.direction.equalsIgnoreCase("notMoving"))){
                        if(lift3.addPerson(p1)){
                            return true;
                        }
                    }if((lift4.direction.equalsIgnoreCase("notMoving"))){
                        if(lift4.addPerson(p1)){
                            return true;
                        }
                    }if((lift5.direction.equalsIgnoreCase("notMoving"))){
                        if(lift5.addPerson(p1)){
                            return true;
                        }
                    }
                }
            }
        }            
        return false; //all lifts full or in wrong direction
    }
}
