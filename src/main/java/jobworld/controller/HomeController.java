package jobworld.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import jobworld.model.entities.Company;
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.Person;
import jobworld.model.entities.User;
import jobworld.model.entities.Role.TypeRole;
import jobworld.services.CompanyService;
import jobworld.services.JobOfferService;
import jobworld.services.PersonService;
import jobworld.services.RoleService;
import jobworld.services.UserService;

@Controller
public class HomeController {

	/**
	 * Classe Controllore Home
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
	private CompanyService companyService;
	private RoleService roleService;

	@GetMapping
	public String home(Locale locale, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getName() != "anonymousUser" && auth.getAuthorities().toString().equals("[ROLE_USER]")) {
			Person person = personService.findbyUserId(auth.getName());
			model.addAttribute("person", person);
		}
		List<JobOffer> allJobOffers = this.jobOfferService.findAll();
		List<String> company_image = new ArrayList<String>();
		for (JobOffer job : allJobOffers) {
			company_image.add(job.getCompany().getUser().getImage());
		}
		model.addAttribute("jobOffers", allJobOffers);
		model.addAttribute("image", company_image);

		// Warning: Non ci sono le province dobbiamo risolverlo sulla vista
		// Implementazione delle api rest per ip address in base alla zona di
		// appartenenza;
		// TODO: cambiate l'ip per vedere come la form filter cambia automaticamente i
		// nomi di regione, città, provincia.
		// String ip ="79.18.192.39"; //Abruzzo Atri
		String ip = "37.160.70.194"; // Lazio Roma
		// String ip ="2.235.168.0"; // Nichelino Piemonte
		String uri = "https://ipapi.co/" + ip + "/json/";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		JSONObject obj = new JSONObject(result);
		model.addAttribute("region", obj.getString("region"));
		model.addAttribute("city", obj.getString("city"));

		// Per estrarre ip dal client che effettua la richiesta
		/*
		 * String ip_client=request.getRemoteAddr(); if (ip_client== null) { ip_client =
		 * request.getHeader("X-FORWARDED-FOR"); // Nel caso di collegamento attraverso
		 * proxy serve comunque a trovare un ip }
		 */

		ArrayList<Long> interested = new ArrayList<Long>();
		List<JobOffer> best_three = new ArrayList<JobOffer>();
		for (JobOffer job : allJobOffers) {
			interested.add(this.jobOfferService.getInterested(job));
		}
		for (int i = 0; i < 3; i++) {
			int id_job = interested.indexOf(Collections.max(interested));
			best_three.add(allJobOffers.get(id_job));
			interested.remove(id_job);
		}
		model.addAttribute("best_three", best_three);
		return "home";
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

	@Autowired
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping("/moreinfo/{companyid}/{jobid}")
	public String moreinfo(@PathVariable(value = "jobid") Long jobId, @PathVariable(value = "companyid") Long companyId,
			Model model) {
		JobOffer joboffer = jobOfferService.findbyId(jobId);
		model.addAttribute("joboffer", joboffer);
		return "moreinfo";
	}

	@PostMapping(value = "/filter")
	public String filter(@RequestParam Map<String, String> allParams, Model model) {
		List<JobOffer> jobOffers = this.jobOfferService.filter(allParams.get("region"), allParams.get("province"),
				allParams.get("town"), allParams.get("position"), allParams.get("contractType"),
				allParams.get("minEducationLevel"), allParams.get("minExperience"));
		model.addAttribute("jobOffers", jobOffers);
		return "home";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/add")
	public String add(@RequestParam Map<String, String> allParams) {
		User user = userService.create(allParams.get("email"), userService.encryptPassword(allParams.get("password")), null, null);
		if (allParams.get("type").equals("person")) {
			user.addRole(roleService.getRoleByTypeRole(TypeRole.USER));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			LocalDate birthDate = LocalDate.parse(allParams.get("birthDate"), formatter);
			personService.create(allParams.get("firstName"), allParams.get("secondName"), birthDate,
					allParams.get("number"), null, user);
		} else if (allParams.get("type").equals("company")) {
			user.addRole(roleService.getRoleByTypeRole(TypeRole.COMPANY));
			companyService.create(allParams.get("name"), user);
		}
		return "redirect:/login";

	}

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {
		String errorMessage = null;
		if (error != null) {
			errorMessage = "Username o Password errati!!";
		}
		if (logout != null) {
			// entriamo in questo caso se non specifichiamo una .logoutSuccessUrl in
			// WebSecurityConf.configure
			errorMessage = "Uscita dal sistema avvenuta!!";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "login";
	}

	@PostMapping("/autentication")
	public String autentication(@RequestParam Map<String, String> allParams) {
		User user = this.userService.findByMailandPassword(allParams.get("email"), allParams.get("password"));
		if (user == null) {
			return "redirect:/";
		} else {
			return null;
			// return "redirect:/user/"+user.getId();//TODO l'user non ha piu l'id. adesso
			// l'id è la email
		}
	}

	@GetMapping("/chisiamo")
	public String chisiamo() {
		return "chisiamo";
	}

	@GetMapping("/faq")
	public String faq() {
		return "faq";
	}
}
