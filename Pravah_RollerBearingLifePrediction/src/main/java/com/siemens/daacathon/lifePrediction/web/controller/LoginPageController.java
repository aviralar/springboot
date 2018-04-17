package com.siemens.daacathon.lifePrediction.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.siemens.daacathon.lifePrediction.web.data.User;


@Controller
@ControllerAdvice
public class LoginPageController {
	
	@RequestMapping(value={"/","/home","/index"}, method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/userLogin", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> submitQuizForm(@RequestBody User User, HttpSession session) {
		session.setAttribute("user", User);
		System.out.println(User.getUsername());
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
