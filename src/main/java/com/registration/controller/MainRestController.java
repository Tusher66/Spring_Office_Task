package com.registration.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.registration.model.Engineers;
import com.registration.repository.EngineersRepository;



@Controller
@RequestMapping("/admin/api/v1")
public class MainRestController {
	
	@Autowired
	private EngineersRepository engineersRepo;
	
	@GetMapping("/edit/{id}")
	@ResponseBody
	public ResponseEntity<?> testRestApi(@PathVariable("id")Integer id){
		Optional<Engineers> engineers=engineersRepo.findById(id);
		return ResponseEntity.ok(engineers.isPresent()?engineers.get():null);
	}
	
	
	@PostMapping("/admin/addProgrammer")
	@ResponseBody
	public ResponseEntity<?> addEngineer(@RequestParam int pId, Model model) {
		
		System.out.println(pId);
		
		Optional<Engineers> engineer=engineersRepo.findById(pId);

		model.addAttribute("engineers",engineer.get());
		
		return ResponseEntity.ok(engineer.isPresent()?engineer.get():null);
	}
	
	@DeleteMapping("/deleteProgrammer/{pId}")
	public void deleteStudent(@PathVariable int pId) {
		engineersRepo.deleteById(pId);
	}
	
//	@GetMapping("/deleteProgrammer1/{pId}")
//	@ResponseBody
//	public ResponseEntity<?> deleteProgrammer1(@PathVariable int pId) {
//		
//		Engineers engineers=engineersRepo.deleteById(pId);
//		
//		return ResponseEntity.ok(engineers.isPresent()?engineers.get():null);
//	}
	
	

	

}
