package com.iara.foo.service;

import com.iara.foo.exception.ResourceNotFoundException;
import com.iara.foo.model.Person;
import com.iara.foo.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Person findById(String id) {
        return repository.findById(Long.parseLong(id)).orElseThrow(
                () -> new ResourceNotFoundException("Person with id: " + id + " not found!"));
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person create(Person person) {
        return repository.save(person);
    }

    public Person update(Person person) {
        Person entity = repository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Person with id: " + person.getId() + " not found!")
        );
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());

        return repository.save(entity);
    }

    public void delete(String id) {
        Person person = repository.findById(Long.parseLong(id)).orElseThrow(
                () -> new ResourceNotFoundException("Person with id: " + id + " not found!")
        );

        repository.delete(person);
    }
}
