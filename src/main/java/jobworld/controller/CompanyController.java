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
	//TODO:MODIFICATE IL CAZZO DI UPLOAD PATH ALTRIMENTI VI DA ERRORE!!!!!
	private static String UPLOADED_FOLDER = "C:\\Users\\cicci\\git\\JobWorldAdvance_work\\WebContent\\resources\\img\\companies\\";


	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public String homeCompany(@PathVariable("userId") Long userId,Locale locale , Model model) {
		List<JobOffer> allJobOffers = this.jobOfferService.findAll();
		List<String> company_image = new ArrayList<String>();
		for (JobOffer job : allJobOffers) {
			company_image.add(job.getCompany().getUser().getImage());
		}
		model.addAttribute("jobOffers", allJobOffers);
		model.addAttribute("image", company_image);
		//Warning: Non ci sono le province dobbiamo risolverlo sulla vista
		//Implementazione delle api rest per ip address in base alla zona di appartenenza;
		//TODO: cambiate l'ip per vedere come la form filter cambia automaticamente i nomi di regione, cittï¿½, provincia.
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
		
		String name_company= this.companyService.findbyUserId(userId).getName();
		model.addAttribute("name_company",name_company);
		model.addAttribute("user_id",userId);
		return "company/home";
	}
	
	
	@GetMapping(value="/profile/{userId}")
	public String profile(@PathVariable("userId") Long userId,Model model) {
		Company company=this.companyService.findbyUserId(userId);
		User user=this.userService.findById(userId);
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
	
	
	@GetMapping("/listjoboffer/{userId}")
	public String listjobofferscompany (@PathVariable("userId") Long userId, Model model) {
		long companyId= this.companyService.findbyUserId(userId).getId();
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
