
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
import jobworld.model.entities.Person;
import jobworld.services.PersonService;



@RequestMapping("/JobOffer")
@Controller
public class JobOfferController {
	
private final Logger logger = LoggerFactory.getLogger(JobOfferController.class);
	
	private PersonService personService;
//	private MessageSource messageSource;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model uiModel) {
		logger.info("Listing Person");
		List<Person> allPerson = this.personService.findAll();
		uiModel.addAttribute("people", allPerson);
		logger.info("No. of persons: " + allPerson.size());
		
		return "People/list";
	}
	
	@Autowired
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	
}


