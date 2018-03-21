package com.dgit.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="/doA", method=RequestMethod.GET)
	public String doAGet(Model model){
		logger.info("[doA] GET ===========================================");
		
		return "doA";
	}
	
	@RequestMapping(value="/doB", method=RequestMethod.GET)
	public String doBGet(Model model){
		logger.info("[doB] GET ===========================================");
		model.addAttribute("result", "doB의 result!");
		
		return "doB";
	}
	
	@RequestMapping(value="/test1", method=RequestMethod.GET)
	public String test1Get(Model model){
		logger.info("[test1] GET ===========================================");
		model.addAttribute("result", "확인 중");
		
		return "test1";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginGet(Model model){
		logger.info("[login] GET ===========================================");
		
		 
		return "login";
	}
	
}
