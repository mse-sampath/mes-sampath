package com.babytracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/babytracker")
public class BabyRegistrationController {
	
	@Autowired
	BabyRepository repository;
	
	@PostMapping("/babies/registration")
	public Baby postBaby(@RequestBody Baby baby) {
		System.out.println("registering new baby");
		Baby _baby = repository.save(new Baby(baby.getName(), 
										 	  baby.getDateOfBirth(), 
										 	  baby.getGender(),
										 	  baby.getWeight(),
										 	  baby.getHeight(),
										 	  baby.getBloodGroup()));
		System.out.println(_baby);
		return _baby;
	}
	
	@GetMapping(value = "/babies/allbabies")
	public List<Baby> getAllBabies() {
		
		List<Baby> allBabies = new ArrayList<>();
		repository.findAll().forEach(x -> allBabies.add(x));
		
		return allBabies;
	}
	
	@GetMapping(value = "/babies/getababy/{id}")
	public Optional<Baby> getABaby(@PathVariable("id") long id) {
		Optional<Baby> babies = repository.findById(id);		
	    return babies;
	}
	
	@PutMapping(value = "/babies/{id}")
	public ResponseEntity<Baby> updateBaby(@PathVariable("id") long id, @RequestBody Baby baby){
	
		Optional<Baby> babyData = repository.findById(id);
		
		if(babyData.isPresent()) {
			Baby _baby = babyData.get();
			_baby.setBloodGroup(baby.getBloodGroup());
			_baby.setDateOfBirth(baby.getDateOfBirth());
			_baby.setGender(baby.getGender());
			_baby.setHeight(baby.getHeight());
			_baby.setWeight(baby.getWeight());
			_baby.setName(baby.getName());
			return new ResponseEntity<>(repository.save(_baby), HttpStatus.OK);
		}
		else {
			
			Baby _baby = repository.save(new Baby(baby.getName(), 
				 	  baby.getDateOfBirth(), 
				 	  baby.getGender(),
				 	  baby.getWeight(),
				 	  baby.getHeight(),
				 	  baby.getBloodGroup()));
			return new ResponseEntity<>(_baby, HttpStatus.OK);
		
		}
	}
	
	@DeleteMapping(value = "/babies/deleteababy/{id}")
	public ResponseEntity<String> deleteBaby(@PathVariable("id") long id){
		Optional<Baby> baby = repository.findById(id);
		if(baby.isPresent()) {
			repository.deleteById(id);
			return new ResponseEntity<>("Baby selected is deleted", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("No Baby Available for Deletion", HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping(value = "/babies/deleteallbabies")
	public ResponseEntity<String> deleteAllBabies(){
		repository.deleteAll();
		return new ResponseEntity<>("All Babies Deleted", HttpStatus.OK);
	}
}
