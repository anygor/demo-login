package com.empathic.demologin.controller;

import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {
	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return "redirect:/login";
		} else {
			return "home";
		}
	}
}
