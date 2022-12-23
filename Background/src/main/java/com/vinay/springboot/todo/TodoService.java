package com.vinay.springboot.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
@Service
public class TodoService {
	
	private static List<Todo> todos= new ArrayList<Todo>();
	private int todosCount=0;
	static {
		todos.add(new Todo(1,"vinay","AWS",LocalDate.now().plusYears(1),false));
		todos.add(new Todo(2,"vinay","angular",LocalDate.now().plusYears(1),false));
		todos.add(new Todo(3,"vinay","javaScript",LocalDate.now().plusYears(1),false));
	}
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = 
				todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).collect(Collectors.toList());
	}
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) 
	{
		Todo todo = new Todo(++todosCount,username,description,targetDate,done);
		todos.add(todo);
	}
	public void deleteById(int id) 
	{
		Predicate<? super Todo> prediacte =todo->todo.getId()==id;
		todos.removeIf(prediacte);
	}
	public Todo findById(int id) 
	{
		Predicate <? super Todo> prediacte =todo->todo.getId()==id;
		Todo todo=todos.stream().filter(prediacte).findFirst().get();
		return todo;
	}
	public void updateTodo(@Valid Todo todo)
	{
		deleteById(todo.getId());
		todos.add(todo);
	}
}
