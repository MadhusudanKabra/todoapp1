package com.mrk.springboot.todoapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;


@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	private static int todoCounter=0;
	static {
		todos.add(new Todo(++todoCounter,"Madhu","Learn Spring Boot",LocalDate.now().plusYears(1),false));
		
		todos.add(new Todo(++todoCounter,"Madhu","Learn SJ",LocalDate.now().plusYears(1),false));
		
		todos.add(new Todo(++todoCounter,"Madhu","Learn Chess",LocalDate.now().plusYears(1),false));
	}
	
	public List<Todo> findByUsername(String username){
		return todos;
	}
	
	public void AddTodo(String username,String description,LocalDate targetDate , boolean done) {
		Todo todo = new Todo(++todoCounter , username,description,targetDate,done);
		todos.add(todo);
	}
	
	
	public static void deleteTodoById(int id) {
		
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public static Todo findById(int id) {
		// TODO Auto-generated method stub
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public static void updateTodo(@Valid Todo todo) {
		// TODO Auto-generated method stub
		deleteTodoById(todo.getId());
		todos.add(todo);
	}

	
	
	
}
