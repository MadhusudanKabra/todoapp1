package com.mrk.springboot.todoapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginController {
	
		//	@Autowired    Yahape Autowired bhi use kar sakte h pan we are using constructor based AutoWiring
	private AuthenticationService authenticationService;
	
	public loginController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}


	@RequestMapping(value="/" , method = RequestMethod.GET )
	public String welcomePage(ModelMap model) {
		model.put("name", "Madhusudan");
		return "welcome" ;
	}
	
	
	
				// /login Path (GET)
	@RequestMapping(value="/login" , method = RequestMethod.GET )
	public String loginPage() {
		return "login" ;
	}
	
	
	
				//	Login Path (post) yaane ki jab koi submit vutton pe click karega tab ye trigger hoga
	@RequestMapping(value="/login" , method = RequestMethod.POST )
	public String welcomePage(@RequestParam String username , @RequestParam String password , ModelMap model) {
		
		if(authenticationService.authenticate(username, password)) {
			model.put("name", username);
//			model.put("password", password);      Bcoz Its not a good practice to show password on homepage
			return "welcome" ;
		}	
		
		model.put("errorMsg", "Invalid Credentials!! Please Try again");
		return "login";
		
	}
}
