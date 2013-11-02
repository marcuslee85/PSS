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
        this.floor=1;
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
    
    public boolean addPerson(Person p1){
        if(persons.size()>=9){
            return false;
        }
        personsToPickUp.add(p1);
        return true;
    }
    
    public void removePerson(int floor){
        for (int i = 0; i < persons.size(); i++) {
            while(persons.get(i).getDestinationFloor()==floor){
                persons.remove(i);
                if(persons.get(i).getDestinationFloor()!=floor){
                    break;
                }
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
    
    
}
