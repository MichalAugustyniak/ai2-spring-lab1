package com.ai2.ai2lab1.controller;

import com.ai2.ai2lab1.model.Person;
import com.ai2.ai2lab1.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String getPeople(Model model) {
        List<Person> people = personService.getPeople();
        model.addAttribute("people", people);

        return "get-people";
    }

    @GetMapping("/{firstName}/{familyName}")
    public String  getPerson(@PathVariable String firstName,
                             @PathVariable String familyName,
                             Model model
    ) {
        Person person = personService.getPerson(firstName, familyName);
        model.addAttribute("person", person);

        return "get-person";
    }

    @GetMapping("/new")
    public String addFormPerson(Model model) {
        model.addAttribute("person", new Person());

        return "person-form";
    }

    @PostMapping("/save")
    public String addPerson(@ModelAttribute Person person) {
        personService.addPerson(person);

        return "redirect:/people";
    }
}
