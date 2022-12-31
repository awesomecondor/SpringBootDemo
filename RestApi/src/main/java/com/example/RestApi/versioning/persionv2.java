package com.example.RestApi.versioning;

public class persionv2 {
   private Name name;

public Name getName() {
	return name;
}

public persionv2(Name name) {
	super();
	this.name = name;
}

@Override
public String toString() {
	return "persionv2 [name=" + name + "]";
}
}
