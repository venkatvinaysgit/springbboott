package com.vinay.springboot.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
@Service
public interface TodoRepository extends JpaRepository<Todo, Integer> {
	public List<Todo> findByUsername(String username);

}
