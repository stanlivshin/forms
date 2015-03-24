package com.usbzoso.forms.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.usbzoso.forms.domain.user.UserRole;
import com.usbzoso.forms.entities.User;

@Controller
public class MainController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage() {
        return "home";
    }
	
	@ResponseBody
	@RequestMapping(value = "/public/json", method = RequestMethod.GET)
    public void getJson(HttpServletResponse response) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = new User();
		user.setUsername("stan.livshin@gmail.com");
		user.setRole(UserRole.ADMIN);
		PrintWriter out = response.getWriter();
		mapper.writeValue(out, user);
		out.println(User.WithoutPasswordView.class);
    }
	
	@RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String getUsersPage() {
		System.out.println("inside getUserPage()");
        return "home";
    }
}
