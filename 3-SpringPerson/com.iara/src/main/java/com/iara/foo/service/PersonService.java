package com.iara.foo.service;

import com.iara.foo.model.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    public Person findById(String id) {
        Person person = new Person(counter.incrementAndGet(), "Iara", "Santos",
                "117 Marshall Av. East", "Female");

        return person;
    }

    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    private Person mockPerson(int i) {
        Person person = new Person(counter.incrementAndGet(), "Person Name", "Person Last Name",
                "117 Marshall Av. East", i % 2 == 0? "Female" : "Male");
        return person;
    }

    public Person create(Person person) {

        return person;
    }

    public Person update(Person person) {

        return person;
    }

    public void delete(String id) {
        System.out.println("Person deleted id " + id);
    }
}
