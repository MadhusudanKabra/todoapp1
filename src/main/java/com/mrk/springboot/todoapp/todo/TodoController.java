package com.mrk.springboot.todoapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;


@Controller
public class TodoController {
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}
	private TodoService todoService;
	
	
	
	//  /list-todos
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		List<Todo> todos = todoService.findByUsername("mrk");
		model.addAttribute("todos", todos);
		return "listTodos";
	}
	
	/* 				ADD-TODO 				*/
	
	
		// This will Handle all Requests  (GET ,POST
/*	
	 	@RequestMapping("add-todo")
		public String AddNewTodo() {
			
			return "addTodo";
		}
*/	
	
	@RequestMapping(value="add-todo" , method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = (String)model.get("name");
		Todo todo = new Todo(0, username, "Default Desc", LocalDate.now().plusYears(4), false);
		model.put("todo", todo);
		return "addTodo";
	}
	
	//       IMPORTANTTTTTTTTTTTTTTTTTTTTTT
	
	/*
	 	Abhi is case mein we are returning listTodos jsp page , but the todos is not populated and we will get an empty table
		So instead we will redirect it to list-todos path (Wo route ka controller karega handle)	*/
	
/*
 	@RequestMapping(value="add-todo" , method = RequestMethod.POST)

	public String AddNewTodo() {
		
		return "listTodos";
	}
 */
		
		//Validdation ke liye niche alag function use kiya hein
	
//	@RequestMapping(value="add-todo" , method = RequestMethod.POST)
//	public String AddNewTodo( @RequestParam String description  ,ModelMap model) {
//		todoService.AddTodo((String)model.get("name"), description, LocalDate.now().plusYears(2), false);
//		return "redirect:list-todos";
//	}
	
	@RequestMapping(value="add-todo" , method = RequestMethod.POST)
	public String AddNewTodo(ModelMap model , @Valid Todo todo , BindingResult result) {
		
		if(result.hasErrors()) {
			return "addTodo";
		}
		
		todoService.AddTodo((String)model.get("name"), todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:list-todos";
	}
	
	
	
	
	/* 			DELETE TODO BY ID		 */
	
	@RequestMapping("/delete-todo")
	public String deleteTodo(@RequestParam int id) {
		//		Delete Todo
		todoService.deleteTodoById(id);
			
		return "redirect:list-todos";
	}
	
	
	/* 			UPDATE TODO				*/
	
	@RequestMapping(value="update-todo" , method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id , ModelMap model) {
		
		Todo todo = TodoService.findById(id);
		model.addAttribute("todo",todo);
		return "addTodo";
	}
	
	
	@RequestMapping(value="update-todo" , method = RequestMethod.POST)
	public String updateTodo(ModelMap model , @Valid Todo todo, BindingResult result) {

		if(result.hasErrors()) {
			return "addTodo";
		}
		TodoService.updateTodo(todo);
		return "redirect:list-todos";
	}
	
	
	 
	
	
	
}	
