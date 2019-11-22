
package controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import controller.SingerController;
import model.entities.Person;
import services.PersonService;



@RequestMapping("/JobOffer")
@Controller
public class SingerController {
	
private final Logger logger = LoggerFactory.getLogger(SingerController.class);
	
	private PersonService personService;
//	private MessageSource messageSource;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model uiModel) {
		logger.info("Listing Person");
		List<Person> allPerson = this.personService.findAll();
		uiModel.addAttribute("JobOffer", allPerson);
		logger.info("No. of JobOffer: " + allPerson.size());
		
		return "JobOffer/list";
	}
	
	@Autowired
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	
}


