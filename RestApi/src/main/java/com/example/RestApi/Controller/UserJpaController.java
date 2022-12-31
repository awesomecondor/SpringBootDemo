package com.example.RestApi.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.example.RestApi.Bean.Post;
import com.example.RestApi.Bean.Users;
import com.example.RestApi.Exception.UserNotFoundException;
import com.example.RestApi.Repository.PostRepo;
import com.example.RestApi.Repository.UserRepo;

@RestController

public class UserJpaController {
	@Autowired
	private UserRepo repo;
	@Autowired
	private PostRepo postrepo;
	@GetMapping("jpa/users")
	public List<Users> retrieveALLusers(){
		return repo.findAll();
	}
	
	//Entity Model
	//WebMvcLinkbuilder -- These are used as hateoas mainframe
	
	@GetMapping("jpa/users/{id}")
	public EntityModel<Users> findByid(@PathVariable int id) {
		Optional<Users> user = repo.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		EntityModel<Users> entityModel = EntityModel.of(user.get());
		
		WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).retrieveALLusers());
		entityModel.add(link.withRel("All-Users"));
		return entityModel;
		
	}
	@DeleteMapping("jpa/users/{id}")
	public void DeleteUser1(@PathVariable int id) {
		repo.deleteById(id);
	}
	
	@GetMapping("jpa/users/{id}/posts")
	public List<Post> getdetails(@PathVariable int id) {
		Optional<Users> user = repo.findById(id);
		if(user==null)
			throw new UserNotFoundException("id:"+id);
		return user.get().getPosts();
	}

	
	@PostMapping("jpa/users")
	public ResponseEntity<Users> createUser1(@Valid @RequestBody Users user) {
		Users SavedUser = repo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(SavedUser.getId())
				.toUri();
		return ResponseEntity.created(location ).build();
	}
	
	@PostMapping("jpa/users/{id}/posts")
	public ResponseEntity<Object> CreatePosts(@PathVariable int id ,@Valid @RequestBody Post posts ) {
		Optional<Users> user = repo.findById(id);
		if(user==null)
			throw new UserNotFoundException("id:"+id);
		posts.setUser(user.get());
	Post savedPost =	postrepo.save(posts);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location ).build();
	}
}
