package com.example.RestApi.Controller;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.RestApi.Bean.Users;
import com.example.RestApi.Exception.UserNotFoundException;
import com.example.RestApi.Service.UserService;

@RestController

public class UserController {
	@Autowired
	private UserService service;
	@GetMapping("/users")
	public List<Users> retrieveALLusers(){
		return service.findAll();
	}
	
	//Entity Model
	//WebMvcLinkbuilder -- These are used as hateoas mainframe
	
	@GetMapping("/users/{id}")
	public EntityModel<Users> findOne(@PathVariable int id) {
		Users user = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id:"+id);
		EntityModel<Users> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).retrieveALLusers());
		entityModel.add(link.withRel("All-Users"));
		return entityModel;
		
	}
	@DeleteMapping("/users/{id}")
	public void DeleteUser(@PathVariable int id) {
		service.DeleteById(id);
	}
	@PostMapping("/users")
	public ResponseEntity<Users> createUser(@Valid @RequestBody Users user) {
		Users SavedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(SavedUser.getId())
				.toUri();
		return ResponseEntity.created(location ).build();
	}
}
