package com.empathic.demologin.controller;

import com.empathic.demologin.model.shiro.ShiroClient;
import com.empathic.demologin.service.LoginService;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

	private final LoginService loginService;

	@GetMapping
	public String view() {
		return "login";
	}

	@RequestMapping(value = "signin", method = RequestMethod.POST)
	public String signIn(@ModelAttribute("shiroClient")ShiroClient form, ModelMap model) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			ShiroClient client = loginService.getShiroClientByUsername(form.getUsername());
			if (client.getPasswordHash().equals(loginService.getCipher(form.getPasswordHash(), client.getSalt()))) {
				UsernamePasswordToken token = new UsernamePasswordToken(form.getUsername(), client.getPasswordHash());
				try {
					subject.login(token);
				} catch (AuthenticationException ae) {
					ae.printStackTrace(); // TODO add logger
					return "redirect:/login";
				}
				return "home";
			}
		}
 		return "redirect:/login";
	}
}
