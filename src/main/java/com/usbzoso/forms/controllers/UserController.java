package com.usbzoso.forms.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.usbzoso.forms.domain.user.UserCreateForm;
import com.usbzoso.forms.domain.user.UserCreateFormValidator;
import com.usbzoso.forms.service.user.UserService;

@Controller
public class UserController {
	
	private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;

    @Autowired
    public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator) {
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
    }
    
    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
		return new ModelAndView("user/signup", "form", new UserCreateForm());
    }
	
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user/signup";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            bindingResult.reject("email.exists", "Email already exists");
            return "user/signup";
        }
        // ok, redirect
        return "redirect:/user/users";
    }
    
    @RequestMapping(value = "/user/users", method = RequestMethod.GET)
    public ModelAndView getUsersPage() {
        return new ModelAndView("user/users", "users", userService.getAllUsers());
    }
}
