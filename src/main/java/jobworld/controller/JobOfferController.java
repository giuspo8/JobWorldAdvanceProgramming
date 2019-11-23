
package jobworld.controller;
/**
 * Controllore 2
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jobworld.controller.JobOfferController;
import jobworld.model.entities.JobOffer;
import jobworld.services.JobOfferService;



@RequestMapping("/jobOffers")
@Controller
public class JobOfferController {
	
private final Logger logger = LoggerFactory.getLogger(JobOfferController.class);
	
	private JobOfferService jobOfferService;
//	private MessageSource messageSource;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model uiModel) {
		logger.info("Listing JobOffers");
		List<JobOffer> allJobOffers = this.jobOfferService.findAll();
		uiModel.addAttribute("jobOffers", allJobOffers);
		logger.info("No. of jobOffers: " + allJobOffers.size());
		
		return "jobOffers/list";
	}
	
	@Autowired
	public void setJobOfferService(JobOfferService jobOfferService) {
		this.jobOfferService = jobOfferService;
	}
	
	
}


