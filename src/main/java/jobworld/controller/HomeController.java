package jobworld.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


import jobworld.model.entities.JobOffer;
import jobworld.model.entities.User;
import jobworld.services.JobOfferService;
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
	@RequestMapping(method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		List<JobOffer> allJobOffers = this.jobOfferService.findAll();
		model.addAttribute("jobOffers", allJobOffers);
		//Warning: Non ci sono le province dobbiamo risolverlo sulla vista
		//Implementazione delle api rest per ip address in base alla zona di appartenenza;
		//TODO: cambiate l'ip per vedere come la form filter cambia automaticamente i nomi di regione, città, provincia.
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
		
		return "home";
	}
	
	@Autowired
	public void setJobOfferService(JobOfferService jobOfferService) {
		this.jobOfferService = jobOfferService;
	}

	@PostMapping(value = "/filter")
	public String filter(@RequestParam Map<String,String> allParams, Model model) {
		List<JobOffer> jobOffers = this.jobOfferService.filter(allParams.get("region"),
				allParams.get("province"), allParams.get("town"),
				allParams.get("position"), allParams.get("contractType"),
				allParams.get("minEducationLevel"), allParams.get("minExperience"));
		model.addAttribute("jobOffers", jobOffers);
		return "home";
	}
	
	@RequestMapping("/chisiamo")
	public String chisiamo(){
		return "chisiamo";
	}
	
	@RequestMapping("/faq")
	public String faq(){
		return "faq";
	}
}
