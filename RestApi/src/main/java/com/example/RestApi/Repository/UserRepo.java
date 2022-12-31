package com.example.RestApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RestApi.Bean.Users;
@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{

}
