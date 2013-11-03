/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pss;

import java.util.ArrayList;

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
        
        //vvip
        //check which lift first to clear
        //add vip to queue
        //lift should not add anymore persons until vip is out of lift queue and lift persons
        if (p1.getType() == 2) {
            if ((lift1.direction.equalsIgnoreCase("notMoving"))) {
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
        }

        //check for vvips so they can be alone
        boolean vvipLift1=false;
        boolean vvipLift2=false;
        boolean vvipLift3=false;
        boolean vvipLift4=false;
        boolean vvipLift5=false;
        ArrayList<Person> lift1All = new ArrayList<Person>();
        ArrayList<Person> lift2All = new ArrayList<Person>();
        ArrayList<Person> lift3All = new ArrayList<Person>();
        ArrayList<Person> lift4All = new ArrayList<Person>();
        ArrayList<Person> lift5All = new ArrayList<Person>();
        lift1All.addAll(getLift1().getPersons());
        lift2All.addAll(getLift2().getPersons());
        lift3All.addAll(getLift3().getPersons());
        lift4All.addAll(getLift4().getPersons());
        lift5All.addAll(getLift5().getPersons());
        lift1All.addAll(getLift1().personsToPickUp);
        lift2All.addAll(getLift2().personsToPickUp);
        lift3All.addAll(getLift3().personsToPickUp);
        lift4All.addAll(getLift4().personsToPickUp);
        lift5All.addAll(getLift5().personsToPickUp);
        for (int i = 0; i < lift1All.size(); i++) {
            if (lift1All.get(i).getType() == 2) {
                vvipLift1 = true;
                break;
            }
        }
        for (int i = 0; i < lift2All.size(); i++) {
            if (lift2All.get(i).getType() == 2) {
                vvipLift2 = true;
                break;
            }
        }
        for (int i = 0; i < lift3All.size(); i++) {
            if (lift3All.get(i).getType() == 2) {
                vvipLift3 = true;
                break;
            }
        }
        for (int i = 0; i < lift4All.size(); i++) {
            if (lift4All.get(i).getType() == 2) {
                vvipLift4 = true;
                break;
            }
        }
        for (int i = 0; i < lift5All.size(); i++) {
            if (lift5All.get(i).getType() == 2) {
                vvipLift5 = true;
                break;
            }
        }
        
        //for black ops lock down
        int liftNumber = 1;
        int numberOfPplInQueue = 10;
        //black ops and management only
        //all lifts on lockdown
        if (p1.type == 1 || p1.type == 3) {
            if (lift1.personsToPickUp.size() < numberOfPplInQueue && !vvipLift1) {
                numberOfPplInQueue = lift1.personsToPickUp.size();
            }
            if (lift2.personsToPickUp.size() < numberOfPplInQueue && !vvipLift2) {
                numberOfPplInQueue = lift2.personsToPickUp.size();
                liftNumber = 2;
            }
            if (lift3.personsToPickUp.size() < numberOfPplInQueue && !vvipLift3) {
                numberOfPplInQueue = lift3.personsToPickUp.size();
                liftNumber = 3;
            }
            if (lift4.personsToPickUp.size() < numberOfPplInQueue && !vvipLift4) {
                numberOfPplInQueue = lift4.personsToPickUp.size();
                liftNumber = 4;
            }
            if (lift5.personsToPickUp.size() < numberOfPplInQueue && !vvipLift5) {
                liftNumber = 5;
            }

            if (liftNumber == 1) {
                if (lift1.addPerson(p1)) {
                    return true;
                }
            } else if (liftNumber == 2) {
                if (lift2.addPerson(p1)) {
                    return true;
                }
            } else if (liftNumber == 3) {
                if (lift3.addPerson(p1)) {
                    return true;
                }
            } else if (liftNumber == 4) {
                if (lift4.addPerson(p1)) {
                    return true;
                }
            } else {
                if (lift5.addPerson(p1)) {
                    return true;
                }
            }
        }

        
        
        if (p1.getArrivalFloor() < p1.getDestinationFloor()) {
            //going up
            if (lift1.direction.equalsIgnoreCase("up") && lift1.floor < p1.getArrivalFloor() && !vvipLift1) {
                if (lift1.addPerson(p1)) {
                    return true;
                }
            } else if (lift2.direction.equalsIgnoreCase("up") && lift2.floor < p1.getArrivalFloor() && !vvipLift2) {
                if (lift2.addPerson(p1)) {
                    return true;
                }
            } else if (lift3.direction.equalsIgnoreCase("up") && lift3.floor < p1.getArrivalFloor() && !vvipLift3) {
                if (lift3.addPerson(p1)) {
                    return true;
                }
            } else if (lift4.direction.equalsIgnoreCase("up") && lift4.floor < p1.getArrivalFloor() && !vvipLift4) {
                if (lift4.addPerson(p1)) {
                    return true;
                }
            } else if (lift5.direction.equalsIgnoreCase("up") && lift5.floor < p1.getArrivalFloor() && !vvipLift5) {
                if (lift5.addPerson(p1)) {
                    return true;
                }
            }
        }
        if (p1.getArrivalFloor() > p1.getDestinationFloor()) {
            if (lift1.direction.equalsIgnoreCase("down") && lift1.floor > p1.getArrivalFloor() && !vvipLift1) {
                if (lift1.addPerson(p1)) {
                    return true;
                }
            } else if (lift2.direction.equalsIgnoreCase("down") && lift2.floor > p1.getArrivalFloor() && !vvipLift2) {
                if (lift2.addPerson(p1)) {
                    return true;
                }
            } else if (lift3.direction.equalsIgnoreCase("down") && lift3.floor > p1.getArrivalFloor() && !vvipLift3) {
                if (lift3.addPerson(p1)) {
                    return true;
                }
            } else if (lift4.direction.equalsIgnoreCase("down") && lift4.floor > p1.getArrivalFloor() && !vvipLift4) {
                if (lift4.addPerson(p1)) {
                    return true;
                }
            } else if (lift5.direction.equalsIgnoreCase("down") && lift5.floor > p1.getArrivalFloor() && !vvipLift5) {
                if (lift5.addPerson(p1)) {
                    return true;
                }
            }
        }

        if (lift1.direction.equalsIgnoreCase("notMoving") && !vvipLift1) {
            if (lift1.addPerson(p1)) {
                return true;
            }
        }
        if (lift2.direction.equalsIgnoreCase("notMoving") && !vvipLift2) {
            if (lift2.addPerson(p1)) {
                return true;
            }
        }
        if (lift3.direction.equalsIgnoreCase("notMoving") && !vvipLift3) {
            if (lift3.addPerson(p1)) {
                return true;
            }
        }
        if (lift4.direction.equalsIgnoreCase("notMoving") && !vvipLift4) {
            if (lift4.addPerson(p1)) {
                return true;
            }
        }
        if (lift5.direction.equalsIgnoreCase("notMoving") && !vvipLift5) {
            if (lift5.addPerson(p1)) {
                return true;
            }
        }

        return false; //all lifts full or in wrong direction
    }

    public int liftGetNextStep(int i1) {
        if (i1 == 1) {
            return lift1.nextStep();
        }
        if (i1 == 2) {
            return lift2.nextStep();
        }
        if (i1 == 3) {
            return lift3.nextStep();
        }
        if (i1 == 4) {
            return lift4.nextStep();
        }
        if (i1 == 5) {
            return lift5.nextStep();
        }
        return 0; //fatal error
    }

    public int liftGetNumberOfPersons(int i1) {
        if (i1 == 1) {
            return lift1.persons.size();
        }
        if (i1 == 2) {
            return lift2.persons.size();
        }
        if (i1 == 3) {
            return lift3.persons.size();
        }
        if (i1 == 4) {
            return lift4.persons.size();
        }
        if (i1 == 5) {
            return lift5.persons.size();
        }
        return 0; //fatal error
    }
}
