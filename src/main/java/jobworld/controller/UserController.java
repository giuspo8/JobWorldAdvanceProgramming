package jobworld.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jobworld.model.entities.Curriculum;
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.Person;
import jobworld.model.entities.User;
import jobworld.services.CurriculumService;
import jobworld.services.JobOfferService;
import jobworld.services.PersonService;
import jobworld.services.UserService;
import jobworld.utils.UtilityForController;


@Controller
@RequestMapping("/user")
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
	private static String UPLOADED_FOLDER = "/Users/giulianilorenzo/Documents/eclipse-workspace/JobWorldAdvanceProgramming/WebContent/resources/img/users/";
	
	
	@GetMapping("/profile")
	public String profile(@RequestParam(value="date", defaultValue = "" , required = false) String date_error,
			@RequestParam(value="con", defaultValue = "" , required = false) String con, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Person person = personService.findbyUserId(auth.getName());
		User user= userService.findByEmail(auth.getName());
		String date = UtilityForController.localdatetostringdate(person.getBirthDate());
		model.addAttribute("date_error",date_error);
		model.addAttribute("date", date);
		model.addAttribute("person",person);
		model.addAttribute("con", con);
		model.addAttribute("user",user);
		return "user/profile";
	}
	
	@PostMapping("/update")
	public String update(@RequestParam Map<String,String> allParams, @RequestParam("image") MultipartFile image, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.update(userService.findByEmail(auth.getName()));
		boolean trovato=true;
		int i=image.getOriginalFilename().length()-1;
		while(trovato & i>=0) {
			char temp=image.getOriginalFilename().charAt(i);
			if (temp=='\\' || i==0) {
				trovato=false;
			}
			i--;
		}
		try {
			byte[] bytes = image.getBytes();
			String name_image= image.getOriginalFilename().substring(i+1);
	        Path path_disk = Paths.get(UPLOADED_FOLDER + name_image);
	        String path_image = "resources/img/users/"+ name_image;
	        Files.write(path_disk, bytes);
	        user.setImage(path_image);
		} catch(IOException e) {
			 e.printStackTrace();
		}
        user.setDescription(allParams.get("description"));			
        user = userService.update(user);
		Person person = personService.findbyUserId(auth.getName());
        person.setFirstName(allParams.get("firstName"));
        person.setSecondName(allParams.get("secondName"));
        person.setNumber(allParams.get("number"));
        try {
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        	LocalDate birthDate = LocalDate.parse(allParams.get("birthDate"), formatter);
        	person.setBirthDate(birthDate);
		}
		catch (DateTimeParseException e){
			return "redirect:/user/profile?date=true";
		}
        try {
        person = personService.update(person);
        } catch(ConstraintViolationException e) {
        	return "redirect:/user/profile?con=true";
        }
        model.addAttribute("user",user);
		model.addAttribute("person", person);
		return "redirect:/user/profile";
	}
	
	@GetMapping("/curriculum")
	public String curriculum(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Person person = personService.findbyUserId(auth.getName());
		Curriculum curriculum = person.getCurriculum();
		model.addAttribute("curriculum", curriculum);
		model.addAttribute("email", person.getUser().getEmail());
		return "user/curriculum";
	}
	
	@PostMapping("/createCurriculum")
	public String createCurriculum(@RequestParam Map<String,String> allParams, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Person person = personService.findbyUserId(auth.getName());
		Curriculum curriculum = curriculumService.create(
				new Curriculum(person, allParams.get("workExperience"), allParams.get("education"), allParams.get("personalSkills"), allParams.get("additionalInfo")));
		model.addAttribute("curriculum", curriculum);
		return "redirect:/user/curriculum?email=" + allParams.get("email");
	}
	
	@PostMapping("/updateCurriculum")
	public String updateCurriculum(@RequestParam Map<String,String> allParams, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Person person = personService.findbyUserId(auth.getName());
		Curriculum curriculum = person.getCurriculum();
		curriculum.setWorkExperience(allParams.get("workExperience"));
		curriculum.setEducation(allParams.get("education"));
		curriculum.setPersonalSkills(allParams.get("personalSkills"));
		curriculum.setAdditionalInfo(allParams.get("additionalInfo"));
		curriculum = curriculumService.update(curriculum);
		model.addAttribute("curriculum", curriculum);
		return "redirect:/user/curriculum";
	}
	
	@GetMapping("/apply/{jobid}")
	public String apply(@PathVariable("jobid") Long jobId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Person person = personService.findbyUserId(auth.getName());
		JobOffer joboffer = jobOfferService.findbyId(jobId);
		person=personService.apply(person, joboffer);
		return "redirect:/#"+joboffer.getId();
		
	}
	
	@GetMapping("/unapply/{jobid}")
	public String unapply(@PathVariable("jobid") Long jobId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Person person = personService.findbyUserId(auth.getName());
		JobOffer joboffer = jobOfferService.findbyId(jobId);
		person=personService.unapply(person, joboffer);
		return "redirect:/#"+joboffer.getId();
		
	}
	
	@GetMapping("/deleteCurriculum")
	public String deleteCurriculum() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Person person = personService.findbyUserId(auth.getName());
		Curriculum curriculum = person.getCurriculum();
		curriculumService.delete(curriculum);
		return "redirect:/user/curriculum";
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
