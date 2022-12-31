package com.example.RestApi.versioning;

public class persionv1 {
    private String name;

	public String getName() {
		return name;
	}

	public persionv1(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "persionv1 [name=" + name + "]";
	}
    
}
