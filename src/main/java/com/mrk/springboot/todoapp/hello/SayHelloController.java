package com.mrk.springboot.todoapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	
//	On /say-hello route It should return a text
	
	@RequestMapping("/say-hello")
	@ResponseBody    // Ye Function ki return string as it is as a response send karneke liye
	public String sayHello() {
		return "Hello Dostoo !!! " ;
	}
	
	@RequestMapping("say-hello-html")
	@ResponseBody 
	public String sayHelloHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title> My First HTML Page - Changed</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("My first html page with body - Changed");
		sb.append("</body>");
		sb.append("</html>");
		
		return sb.toString();
	}
	
	
	@RequestMapping("say-hello-jsp")
//	@ResponseBody     // Iski Jarurat Nhi bcoz we are searching for view with name sayHello and We don't want to directly return the string
	public String sayHelloJsp() {
		return "sayHello";
	}
	
	
}
