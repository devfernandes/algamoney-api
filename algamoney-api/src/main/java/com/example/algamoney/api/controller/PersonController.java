package com.example.algamoney.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoney.api.exception.ResourceNotFoundException;
import com.example.algamoney.api.model.Person;
import com.example.algamoney.api.repository.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonController {

	@Autowired
	PersonRepository personRepository;

	@GetMapping
	public List<Person> getAllPeople() {
		return personRepository.findAll();
	}

	@GetMapping("/{id}")
	public Person getPersonById(@PathVariable Long id) {
		return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa", "id", id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable Long id) {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa", "id", id));
		personRepository.delete(person);
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Person> postPerson(@Valid @RequestBody Person person) {
		Person savedPerson = personRepository.save(person);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(savedPerson.getId())
				.toUri();

		return ResponseEntity.created(uri).body(savedPerson);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Person> putPerson(@Valid @RequestBody Person newPerson, @PathVariable Long id){
		return personRepository.findById(id).map(person -> {
			person.setName(newPerson.getName());
			person.setActive(newPerson.getActive());
			person.getAddress().setCEP(newPerson.getAddress().getCEP());
			person.getAddress().setCity(newPerson.getAddress().getCity());
			person.getAddress().setComplement(newPerson.getAddress().getComplement());
			person.getAddress().setNeighborhood(newPerson.getAddress().getNeighborhood());
			person.getAddress().setNumber(newPerson.getAddress().getNumber());
			person.getAddress().setPublicPlace(newPerson.getAddress().getPublicPlace());
			person.getAddress().setState(newPerson.getAddress().getState());
			personRepository.save(person);
			return ResponseEntity.ok(person);			
		}).orElseThrow(() -> new ResourceNotFoundException("Pessoa", "id", id));
	}
}
