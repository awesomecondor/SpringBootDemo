package com.example.RestApi.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.example.RestApi.Bean.Users;

@Service
public class UserService {
	private static List<Users> user = new ArrayList<>();
	private static int userCount=0;
	static {
		user.add(new Users(++userCount, "Adam", LocalDate.now()));
		user.add(new Users(++userCount, "Jimmy", LocalDate.now()));
		user.add(new Users(++userCount, "Jiya", LocalDate.now()));

	}

	// FindAll
	public List<Users> findAll() {
		return user;
	}
	
	//Save
	
	public Users save(Users users) {
		user.add(users);
		return users;
	}
	//FindById
	public Users findOne(int id) {
		Predicate<? super Users> predicate = user -> user.getId().equals(id);
		return user.stream().filter(predicate).findFirst().orElse(null);

	}
	public void DeleteById(int id) {
		Predicate<? super Users> predicate = user -> user.getId().equals(id);
		user.removeIf(predicate);
		
	}
}