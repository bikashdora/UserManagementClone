package com.talentica.resam.auth.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talentica.resam.auth.service.ResamUserDetailsImpl;
import com.talentica.resam.auth.service.ResamUserDetailsServiceImpl;

@Controller
public class AuthController {
	
	@Autowired
	private ResamUserDetailsServiceImpl resamUserDetailsServiceImpl;
	
	@RequestMapping("/welcome")
	public String welcome(Model model) 
	{
		return "welcome";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		model.addAttribute("user", new ResamUserDetailsImpl());
		return "login";
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") ResamUserDetailsImpl user, Model model, HttpServletRequest request) 
	{
		String view = "login";
		
		ResamUserDetailsImpl loginUser= (ResamUserDetailsImpl) resamUserDetailsServiceImpl.loadUserByUsername(user.getUsername());
		
		if(loginUser != null)
		{
			request.getSession().setAttribute("LOGIN_USER", loginUser);
			view = "redirect:/userhome.htm";
		}
		else
		{
			model.addAttribute("error", "Your login attempt was not successful, try again");
		}
		return view;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/login.htm";
	}	
	

}
