package com.sudip.familytree.relations;

import com.sudip.familytree.entities.Person;
import com.sudip.familytree.enums.Gender;

import java.util.List;
import java.util.stream.Collectors;

public class PaternalUncleRelationship implements RelationShip {
    @Override
    public List<Person> forPerson(Person person) {
        return person.getFather().getFather()
                .getChildren()
                .stream()
                .filter(each -> each.getGender().equals(Gender.MALE))
                .filter(each -> !each.equals(person.getFather()))
                .collect(Collectors.toList());
    }
}
