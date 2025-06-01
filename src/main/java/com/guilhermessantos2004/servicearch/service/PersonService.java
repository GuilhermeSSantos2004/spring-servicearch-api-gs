package com.guilhermessantos2004.servicearch.service;

import com.guilhermessantos2004.servicearch.model.Person;
import com.guilhermessantos2004.servicearch.repository.PersonRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional; // <-- Adicione esta linha

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public Optional<Person> getById(Long id) {
        return personRepository.findById(id);
    }

    public void deleteById(Long id) {
    personRepository.deleteById(id);
    }

}
