package com.example.RestApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RestApi.Bean.Post;
@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

}
