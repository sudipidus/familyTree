package com.sudip.familytree.process;

import com.sudip.familytree.entities.Person;
import com.sudip.familytree.enums.Gender;

import java.util.HashMap;
import java.util.Map;

public class RelationshipFlow {
    private Map<String,RelationShip> relationShipMap;

    public RelationshipFlow(){
        relationShipMap = new HashMap<>();
        RelationShip fatherRelationShip = person -> person.getFather();
        RelationShip motherRelationship = person -> person.getMother();
        RelationShip sisterRelationShip = person -> person.getMother().getChildren().stream()
                .filter(each -> each.getGender().equals(Gender.FEMALE))
                .findAny().get();
        RelationShip brotherRelationship = person -> person.getMother().getChildren().stream()
                .filter(each -> each.getGender().equals(Gender.MALE))
                .findAny().get();
        RelationShip maternalAuntRelationship = person -> person.getMother().getMother()
                .getChildren().stream()
                .filter(each -> each.getGender().equals(Gender.FEMALE))
                .filter(each->!each.equals(person.getMother()))
                .findAny().get();




        relationShipMap.put("father", fatherRelationShip);
        relationShipMap.put("mother", motherRelationship);
        relationShipMap.put("sister", sisterRelationShip);
        relationShipMap.put("brother", brotherRelationship);
        relationShipMap.put("Maternal-Aunt",maternalAuntRelationship);




    }


    public Person findByRelation(Person person, String relationship){
        RelationShip relationShip = relationShipMap.get(relationship);
        return relationShip.fetchRelation(person);
    }

}