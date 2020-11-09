package com.babytracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/babytracker")
public class BabyRegistrationController {
	
	@Autowired
	BabyRepository repository;
	
	@PostMapping(value = "/registration/create")
	public Baby postBaby(@RequestBody Baby baby) {
		System.out.println("registering new baby");
		Baby _baby = repository.save(new Baby(baby.getName(), baby.getDateOfBirth(), baby.getGender()));
		repository.save(baby);
		return _baby;
	}
	
	@GetMapping(value = "/registration")
	public String message() {
		return "Baby Registration";
	}
}
