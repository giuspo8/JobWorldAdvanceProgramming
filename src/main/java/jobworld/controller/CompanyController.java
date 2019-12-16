package jobworld.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import jobworld.model.entities.User;
import jobworld.services.CompanyService;
import jobworld.services.JobOfferService;
import jobworld.services.UserService;


/**
 * Classe Controllore User
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */


@RequestMapping("/company")
@Controller
public class CompanyController {

	private JobOfferService jobOfferService;
	private CompanyService companyService;
	private UserService userService;
	//TODO:MODIFICATE L'UPLOAD PATH ALTRIMENTI VI DA ERRORE!!!!!
	private static String UPLOADED_FOLDER = "C:\\Users\\cicci\\git\\JobWorldAdvance_work\\WebContent\\resources\\img\\companies\\";

	
	
	@GetMapping(value="/profile")
	public String profile(@RequestParam(value="email") String email,Model model) {
		Company company=this.companyService.findbyUserId(email);
		User user=this.userService.findByEmail(email);
		model.addAttribute("company",company);
		model.addAttribute("user",user);
		return "company/profile";
	}
	
	@PostMapping("/update")
	public String update(@RequestParam Map<String,String> allParams, @RequestParam("image") MultipartFile image) {
		try {
			byte[] bytes = image.getBytes();
	        Path path = Paths.get(UPLOADED_FOLDER + image.getOriginalFilename());
	        Files.write(path, bytes);
			System.out.println("You successfully uploaded '" + image.getOriginalFilename() + "'");
			return "company/profile"; // per il momento inserito così lo cambio
		} catch(IOException e) {
			 e.printStackTrace();
		}
		return "company/profile";
	}
	
	
	@GetMapping("/listjoboffer/{email}")
	public String listjobofferscompany (@PathVariable("email") String email, Model model) {
		email=email+".com";
		long companyId= this.companyService.findbyUserId(email).getId();
		List<JobOffer> jobs = this.jobOfferService.findbyCompanyId(companyId);
		model.addAttribute("jobs",jobs);
		return "company/listjoboffer";
	}
	
	@GetMapping("/joboffer/{jobId}")
	public String joboffercompany (@PathVariable("jobId") Long jobId, Model model) {
		JobOffer job = this.jobOfferService.findbyId(jobId);
		model.addAttribute("job",job);
		return "company/editjoboffer";
	}
	
	@Autowired
	public void setJobOfferService(JobOfferService jobOfferService) {
		this.jobOfferService = jobOfferService;
	}
	@Autowired
	public void setCompanyService(CompanyService companyService) {
		this.companyService =companyService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService =userService;
	}
}
