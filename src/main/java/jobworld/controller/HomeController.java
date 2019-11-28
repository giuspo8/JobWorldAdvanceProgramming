package jobworld.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
}
