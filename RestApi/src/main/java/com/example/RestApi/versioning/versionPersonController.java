package com.example.RestApi.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class versionPersonController {
	
	@GetMapping("/v1/person")
	public persionv1 getFirstVersionPersion() {
		return new persionv1("bob charlie");
	}
	@GetMapping("/v2/person")
	public persionv2 getSecondVersionPersion() {
		return new persionv2(new Name("Bob" , "Charlie"));
	}
	
	
	

}
