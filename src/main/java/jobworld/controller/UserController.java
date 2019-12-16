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
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.Person;
import jobworld.model.entities.User;
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
	@SuppressWarnings("unused")
	private UserService userService;
	private PersonService personService;
	

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public String homeUser(@PathVariable("userId") Long userId,Locale locale , Model model) {
		List<JobOffer> allJobOffers = this.jobOfferService.findAll();
		List<String> company_image = new ArrayList<String>();
		for (JobOffer job : allJobOffers) {
			company_image.add(job.getCompany().getUser().getImage());
		}
		model.addAttribute("jobOffers", allJobOffers);
		model.addAttribute("image", company_image);
		
		
		//Warning: Non ci sono le province dobbiamo risolverlo sulla vista
		//Implementazione delle api rest per ip address in base alla zona di appartenenza;
		//TODO: cambiate l'ip per vedere come la form filter cambia automaticamente i nomi di regione, citt�, provincia.
		//String ip ="79.18.192.39";  //Abruzzo Atri
		String ip ="37.160.70.194";   //Lazio Roma
		//String ip ="2.235.168.0";	// Nichelino Piemonte
		String uri = "https://ipapi.co/"+ip+"/json/";
		RestTemplate restTemplate = new RestTemplate();
		String result= restTemplate.getForObject(uri, String.class);
		JSONObject obj = new JSONObject(result);
		System.out.println(obj.getString("region"));
		model.addAttribute("region", obj.getString("region"));
		model.addAttribute("city", obj.getString("city"));
						    
		//Per estrarre ip dal client che effettua la richiesta
		/*
		String ip_client=request.getRemoteAddr();
		if (ip_client== null) {
			ip_client = request.getHeader("X-FORWARDED-FOR");   // Nel caso di collegamento attraverso proxy serve comunque a trovare un ip
		}*/
		
		ArrayList<Long> interested= new ArrayList<Long>();
		List<JobOffer> best_three= new ArrayList<JobOffer>();
		for (JobOffer job : allJobOffers) {
			interested.add(this.jobOfferService.getInterested(job));
		}
		for (int i=0; i<3; i++) {
			int id_job = interested.indexOf(Collections.max(interested));
			best_three.add(allJobOffers.get(id_job));
			interested.remove(id_job);
		}
		model.addAttribute("best_three",best_three);
		
		String firstName= this.personService.findById(userId).getFirstName();
		String secondName=this.personService.findById(userId).getSecondName();
		model.addAttribute("firstName",firstName);
		model.addAttribute("secondName",secondName);
		return "user/home";
	}
	
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
		return "user/profile"; // per il momento inserito cos� lo cambio
	}
	
	@GetMapping("/curriculum")
	public String curriculum() {
		return "user/curriculum";
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
