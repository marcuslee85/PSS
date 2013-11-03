package pss;

import java.util.ArrayList;

/**
 *
 * @author marcus
 */
public class Lift {

    int number;
    int floor;
    String direction; //up or down or not moving only
    ArrayList<Person> persons; //maximum of 10
    ArrayList<Person> personsToPickUp;

    //add person method, check for max
    //
    public Lift(int number) {
        this.number = number;
        this.floor = 1;
        this.direction = "notMoving";
        this.persons = new ArrayList<Person>();
        this.personsToPickUp = new ArrayList<Person>();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirectionUp() {
        this.direction = "up";
    }

    public void setDirectionDown() {
        this.direction = "down";
    }

    public void setDirectionNotMoving() {
        this.direction = "notMoving";
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> Persons) {
        this.persons = Persons;
    }

    public boolean addPerson(Person p1) {
        if ((persons.size() + personsToPickUp.size()) >= 10) {
            return false;
        } else {
            personsToPickUp.add(p1);
            return true;
        }
    }

    private void pickUpPerson() {
        for (int i = 0; i < personsToPickUp.size(); i++) {
            if (personsToPickUp.get(i).getArrivalFloor() == floor) {
                persons.add(personsToPickUp.get(i));
                personsToPickUp.remove(i);
            }
        }
    }

    public void removePerson() {
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getDestinationFloor() == floor) {
                persons.remove(i);
            }
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int nextStep() {
        //move one floor depending on up or down, or notmoving den move to the queue
        //change to not moving when queue and persons empty. DONE!
        //drop off people and den pick up people after movement
        if (direction.equalsIgnoreCase("up") || direction.equalsIgnoreCase("down")) {
            if (persons.isEmpty() && personsToPickUp.isEmpty()) {
                setDirectionNotMoving();
                return floor;
            }
            if (!persons.isEmpty() || !personsToPickUp.isEmpty()){
                if (direction.equalsIgnoreCase("up")) {
                    if (floor == 12) {
                        setDirectionNotMoving();
                    }else{
                        floor++;
                    }
                    if (floor == 0) {
                        floor = 1;
                    }
                    removePerson(); //reached
                    pickUpPerson(); //move from queue to lift
                    return floor;
                } else if (direction.equalsIgnoreCase("down")) {
                    if (floor == -2) {
                        setDirectionNotMoving();
                    }else{
                        floor--;
                    }
                    if (floor == 0) {
                        floor = -1;
                    }
                    removePerson(); //reached
                    pickUpPerson(); //move from queue to lift
                    return floor;
                }
            }
        }
        if (direction.equalsIgnoreCase("notMoving")) {
            if (!persons.isEmpty()) {
                if (persons.get(0).getDestinationFloor() > floor) {
                    setDirectionUp();
                }
                if (persons.get(0).getDestinationFloor() < floor) {
                    setDirectionDown();
                }
            } else {
                if (!personsToPickUp.isEmpty()) {
                    if (personsToPickUp.get(0).getArrivalFloor() > floor) {
                        setDirectionUp();
                    }
                    if (personsToPickUp.get(0).getArrivalFloor() < floor) {
                        setDirectionDown();
                    }
                }
            }
        }
        return floor;
    }
}
