package com.registration.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.registration.model.Engineers;
import com.registration.model.User;
import com.registration.repository.EngineersRepository;
import com.registration.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EngineersRepository pr;
	
	

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
//		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
//		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
//		modelAndView.setViewName("admin/home");
		
		modelAndView.addObject("engineers",pr.findAll());
		
		
		
		return modelAndView;
	}
	
//	@GetMapping({"/home","/"})
////	@GetMapping("/home")
//	public ModelAndView homePage() {
//		
//		ModelAndView mav=new ModelAndView("index.html");
//		mav.addObject("programmer",pr.findAll());
//		return mav;
////		return "HomePage.html";
//	}
	
	@GetMapping("admin/deleteProgrammer1/{pId}")
	public String deleteProgrammer1(@PathVariable int pId) {
		
		pr.deleteById(pId);
		
		return "redirect:/admin/home";
	}
	
	@GetMapping("/admin/edit/{pId}")
	public String showEditData(@PathVariable("pId") int pId, Model model) {

		Optional<Engineers> pro=pr.findById(pId);
		
		model.addAttribute("engineers",pro);
		
		return "/admin/updatePage";

	}
	
	
	
	@PostMapping("/admin/addProgrammer1")
	public String engInfo(@ModelAttribute Engineers engineers) {
		
		pr.save(engineers);

//		return "redirect:/home";
		return "/admin/home";
	}
	
	@PostMapping("/admin/update")
	public String updateData( @ModelAttribute Engineers engineers, BindingResult result,
	        Model model) {


	        pr.save(engineers);
	        model.addAttribute("engineers", pr.findAll());
		
		return "redirect:/admin/home";
	}
	
	@PostMapping("/admin/findById")
	public String findById(@RequestParam int pId, Model model) {
		
		System.out.println(pId);
		
		Optional<Engineers> q=pr.findById(pId);

		model.addAttribute("engineers",q.get());
		
		return "/admin/updatePage";
	}
	
	
	@GetMapping("/admin/addPage")
	public String addPage() {

		
		return "/admin/addEngineer";

	}
	
	
	@PostMapping("/admin/addProgrammer")
	public String addEngineer(@RequestParam int pId, Model model) {
		
		System.out.println(pId);
		
		Optional<Engineers> q=pr.findById(pId);

		model.addAttribute("engineers",q.get());
		
		return "/admin/home";
	}
	

}