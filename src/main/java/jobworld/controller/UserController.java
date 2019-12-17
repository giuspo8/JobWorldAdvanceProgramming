package jobworld.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import jobworld.model.entities.Company;
import jobworld.model.entities.Curriculum;
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.Person;
import jobworld.model.entities.User;
import jobworld.services.CurriculumService;
import jobworld.services.JobOfferService;
import jobworld.services.PersonService;
import jobworld.services.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
	/**
	 * Classe Controllore User
	 * 
	 * @author Giuseppe Costantini
	 * @author Simone di Saverio
	 * @author Lorenzo Giuliani
	 * @author Savio Feng
	 * @version 1.0
	 */
	private JobOfferService jobOfferService;
	private UserService userService;
	private PersonService personService;
	private CurriculumService curriculumService;
	
	
	@GetMapping("/profile")
	public String profile(@RequestParam(value="email") String email,Model model) {
		User user=this.userService.findByEmail(email);
		Person person=this.personService.findbyUserId(email);
		model.addAttribute("person",person);
		model.addAttribute("user",user);
		return "user/profile";
	}
	
	@PostMapping("/update")
	public String update(@RequestParam Map<String,String> allParams, @RequestParam("image") MultipartFile image) {
		String temp=image.getName();
		System.out.println(temp);
		return "user/profile"; // per il momento inserito così lo cambio
	}
	
	@GetMapping("/curriculum")
	public String curriculum(@RequestParam(value="email") String email, Model model) {
		Person person = personService.findbyUserId(email);
		Curriculum curriculum = person.getCurriculum();
		model.addAttribute("curriculum", curriculum);
		model.addAttribute("email", person.getUser().getEmail());
		return "user/curriculum";
	}
	
	@PostMapping("/createCurriculum")
	public String createCurriculum(@RequestParam Map<String,String> allParams, Model model) {
		Person person = personService.findbyUserId(allParams.get("email"));
		Curriculum curriculum = curriculumService.create(
				new Curriculum(person, allParams.get("workExperience"), allParams.get("education"), allParams.get("personalSkills"), allParams.get("additionalInfo")));
		model.addAttribute("curriculum", curriculum);
		return "redirect:/user/curriculum?email=" + allParams.get("email");
	}
	
	@PostMapping("/updateCurriculum")
	public String updateCurriculum(@RequestParam Map<String,String> allParams, Model model) {
		Person person = personService.findbyUserId(allParams.get("email"));
		Curriculum curriculum = curriculumService.update(person.getCurriculum());
		curriculum.setWorkExperience(allParams.get("workExperience"));
		curriculum.setEducation(allParams.get("education"));
		curriculum.setPersonalSkills(allParams.get("personalSkills"));
		curriculum.setAdditionalInfo(allParams.get("additionalInfo"));
		curriculumService.update(curriculum);
		model.addAttribute("curriculum", curriculum);
		return "redirect:/user/curriculum?email=" + allParams.get("email");
	}
	
	@GetMapping("/apply/{jobid}")
	public String apply(@RequestParam(value="email") String email, @PathVariable("jobid") Long jobId) {
		Person person = personService.findbyUserId(email);
		JobOffer joboffer = jobOfferService.findbyId(jobId);
		personService.apply(person, joboffer);
		return "redirect:/";
		
	}
	
	@GetMapping("/deleteCurriculum")
	public String deleteCurriculum(@RequestParam(value="email") String email) {
		Person person = personService.findbyUserId(email);
		Curriculum curriculum = person.getCurriculum();
		curriculumService.delete(curriculum);
		return "redirect:/user/curriculum?email=" + email;
	}
	
	@Autowired
	public void setCurriculumService(CurriculumService curriculumService) {
		this.curriculumService = curriculumService;
	}
	
	@Autowired
	public void setJobOfferService(JobOfferService jobOfferService) {
		this.jobOfferService = jobOfferService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
}
