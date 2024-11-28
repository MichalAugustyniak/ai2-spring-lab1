package com.ai2.ai2lab1.restcontroller;

import com.ai2.ai2lab1.model.Person;
import com.ai2.ai2lab1.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonRestController {

    private final PersonService personService;

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPeople() {
        return ResponseEntity.ok(personService.getPeople());
    }

    @GetMapping("/{firstName}/{familyName}")
    public ResponseEntity<Person> getPerson(
            @PathVariable String firstName,
            @PathVariable String familyName) {
        return ResponseEntity.ok(personService.getPerson(firstName, familyName));
    }

    @PostMapping
    public ResponseEntity<Void> addPerson(@RequestBody Person person) {
        personService.addPerson(person);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updatePerson(
            @RequestParam("first-name") String firstName,
            @RequestParam("family-name") String familyName,
            @RequestBody Person person
    ) {
        Person updatedPerson = personService.getPerson(firstName, familyName);
        updatedPerson.setFirstName(person.getFirstName());
        updatedPerson.setFamilyName(person.getFamilyName());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePerson(
            @RequestParam("first-name") String firstName,
            @RequestParam("family-name") String familyName
    ) {
        personService.removePerson(firstName, familyName);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
