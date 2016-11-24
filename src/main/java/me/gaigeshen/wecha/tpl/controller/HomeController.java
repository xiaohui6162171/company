package me.gaigeshen.wecha.tpl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author gfz
 *
 */
@Controller
@RequestMapping(path = "/")
public class HomeController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String toHome() { return "redirect:/users/index"; }

}
