package com.guilhermessantos2004.servicearch.controller;

import com.guilhermessantos2004.servicearch.model.Person;
import com.guilhermessantos2004.servicearch.service.PersonService;
import com.guilhermessantos2004.servicearch.service.ViaCepService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;
    private final ViaCepService viaCepService;

    public PersonController(PersonService personService, ViaCepService viaCepService) {
        this.personService = personService;
        this.viaCepService = viaCepService;
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        person.setId(null); // Garante que sempre será criado um novo registro
        return personService.addPerson(person);
    }

    @GetMapping("/{id}/cep")
    public Object consultarCepDaPessoa(@PathVariable Long id) {
        Optional<Person> pessoaOpt = personService.getById(id);
        if (pessoaOpt.isEmpty()) {
            return Map.of("error", "Pessoa não encontrada");
        }
        Person pessoa = pessoaOpt.get();
        if (pessoa.getNcep() == null || pessoa.getNcep().isBlank()) {
            return Map.of("error", "CEP não informado para esta pessoa");
        }
        // Remove todos os caracteres que não são dígitos
        String cep = pessoa.getNcep().replaceAll("\\D", "");
        // Valida se ficou só 8 números
        if (cep.length() != 8) {
            return Map.of("error", "CEP inválido. Use 8 dígitos.");
        }
        return viaCepService.getCepInfo(cep);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        return personService.getById(id)
            .map(person -> {
                person.setExternalId(updatedPerson.getExternalId());
                person.setImagePath(updatedPerson.getImagePath());
                person.setName(updatedPerson.getName());
                person.setStatus(updatedPerson.getStatus());
                person.setIdentified(updatedPerson.getIdentified());
                person.setNcep(updatedPerson.getNcep());
                return personService.addPerson(person);
            })
            .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
    personService.deleteById(id);
    }   




}
