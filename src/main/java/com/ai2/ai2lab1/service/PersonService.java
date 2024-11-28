package com.ai2.ai2lab1.service;


import com.ai2.ai2lab1.exception.NotFoundException;
import com.ai2.ai2lab1.model.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final List<Person> people = new ArrayList<>(2);

    @PostConstruct
    public void initPeople() {
        Person person1 = new Person();
        Person person2 = new Person();

        person1.setFirstName("Jan");
        person1.setFamilyName("Kowalski");
        person2.setFirstName("Anna");
        person2.setFamilyName("Nowak");

        people.add(person1);
        people.add(person2);
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person getPerson(String firstName, String familyName) {
        return people.stream()
                .filter(
                        person -> person.getFirstName().equals(firstName) &&
                                person.getFamilyName().equals(familyName)
                )
                .findAny()
                .orElseThrow(() -> new NotFoundException(
                        String.format("Person of first name %s and family name of %s not found", firstName, familyName)
                ));
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public void setPerson(String firstName, String familyName) {
        Person person = new Person();

        person.setFirstName(firstName);
        person.setFamilyName(familyName);

        people.add(person);
    }

    public void removePerson(String firstName, String familyName) {
        if (!people.removeIf(person -> person.getFirstName().equals(firstName) &&
                person.getFamilyName().equals(familyName))) {
            throw new NotFoundException(
                    String.format("Person of first name %s and family name of %s not found", firstName, familyName)
            );
        }
    }
}
