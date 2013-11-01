package pss;

import java.util.ArrayList;

/**
 *
 * @author marcus
 */
public class Lift {
    String direction; //up or down or not moving only
    ArrayList<Person> persons; //maximum of 10
    
    //add person method, check for max
    //
    public Lift(String direction, ArrayList<Person> Persons) {
        this.direction = direction;
        this.persons = Persons;
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
        this.direction = "NotMoving";
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> Persons) {
        this.persons = Persons;
    }
    
    public void addPerson(Person p1){
        persons.add(p1);
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
}
