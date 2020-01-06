package jobworld.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import jobworld.model.entities.Company;
import jobworld.model.entities.Curriculum;
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.JobOffer.Education;
import jobworld.model.entities.Person;
import jobworld.model.entities.User;
import jobworld.services.CompanyService;
import jobworld.services.JobOfferService;
import jobworld.services.PersonService;
import jobworld.services.UserService;
import jobworld.utils.UtilityForController;


/**
 * Classe Controllore User
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 6.0
 */



@Controller
@RequestMapping("/company")
public class CompanyController {

	private JobOfferService jobOfferService;
	private CompanyService companyService;
	private UserService userService;
	private PersonService personService;
	//TODO:MODIFICATE L'UPLOAD PATH ALTRIMENTI VI DA ERRORE!!!!!
	//private static String UPLOADED_FOLDER = "/Users/giulianilorenzo/Documents/eclipse-workspace/JobWorldAdvanceProgramming/WebContent/resources/img/companies/";
	private static String UPLOADED_FOLDER ="C:\\Users\\HP\\git\\JobWorldAdvanceProgramming\\WebContent\\resources\\img\\companies";
	//private static String UPLOADED_FOLDER ="C:\\Users\\cicci\\git\\JobWorldAdvanceProgramming_tiles\\WebContent\\resources\\img\\companies\\";
	//private static String UPLOADED_FOLDER ="C:\\Users\\HP\\git\\JobWorldAdvanceProgramming\\WebContent\\resources\\img\\companies";
	
	@GetMapping(value="/profile")
	public String profile(@RequestParam(value="con", defaultValue = "" , required = false) String con, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Company company = companyService.findbyUserId(auth.getName());
		User user=userService.findByEmail(auth.getName());
		model.addAttribute("company", company);
		model.addAttribute("con", con);
		model.addAttribute("user",user);
		return "company/profile";
	}
	
	@PostMapping("/update")
	public String update(@RequestParam Map<String,String> allParams, @RequestParam("image") MultipartFile image,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.update(userService.findByEmail(auth.getName()));
		boolean trovato=true;
		String path_image;
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
	        path_image = "/resources/img/companies/"+ name_image;
	        if (path_image != user.getImage()) {
		        Files.write(path_disk, bytes);
		        user.setImage(path_image);
	        } 
		} catch(IOException e) {
			 e.printStackTrace();
		}
		user.setDescription(allParams.get("description"));
        user= userService.update(user);
		Company company = companyService.update(user.getCompany());
        company.setName(allParams.get("nome_azienda"));
        try {
        company= companyService.update(company);
        } catch(ConstraintViolationException e) {
        	return "redirect:/company/profile?con=true";
        }
        model.addAttribute("user",user);
		model.addAttribute("company", company);
		return "redirect:/company/profile";
	}
	
	@GetMapping("/listjoboffer")
	public String listjobofferscompany (Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Company company=companyService.findbyUserId(auth.getName());
		List<JobOffer> jobs = jobOfferService.findbyCompanyId(company.getId());
		model.addAttribute("jobs",jobs);
		return "company/listjoboffer";
	}
	
	@GetMapping("/joboffer")
	public String joboffercompany (Model model) {
		JobOffer job = null;
		model.addAttribute("job",job);
		return "company/editjoboffer";
	}
	
	@GetMapping("/joboffer/{jobId}")
	public String joboffercompany ( @RequestParam(value="date", defaultValue = "" , required = false) String date_error,
			@RequestParam(value="con", defaultValue = "" , required = false) String con, @PathVariable("jobId") Long jobId, Model model) {
		JobOffer job = jobOfferService.findbyId(jobId);
		LocalDate expiringDate= job.getExpiringDate();
		String date = UtilityForController.localdatetostringdate(expiringDate);
		model.addAttribute("date_error",date_error);
		model.addAttribute("date",date);
		model.addAttribute("job",job);
		model.addAttribute("con",con);
		return "company/editjoboffer";
	}
	
	@PostMapping("/joboffer/{jobId}/update")
	public String joboffercompanyupdate (@PathVariable("jobId") Long jobId, @RequestParam Map<String,String> allParams, Model model) {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Company company=companyService.findbyUserId(auth.getName());
		JobOffer job = jobOfferService.update(jobOfferService.findbyId(jobId));		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(allParams.get("expiringDate"),formatter);
			job.setExpiringDate(date);
			job.setPosition(allParams.get("position"));
			job.setContractType(allParams.get("contractType"));
			job.setDescription(allParams.get("description"));
			job.setMinEducationLevel(Education.valueOf(allParams.get("minEducationLevel")));
			job.setMinExperience(allParams.get("minExperience"));
			job.setProvince(allParams.get("province_"));
			job.setRegion(allParams.get("region"));
			job.setTown(allParams.get("town"));
		}
		catch (DateTimeParseException e){
			return "redirect:/company/joboffer/"+ job.getId() + "?date=true";
		}
		try {
		job = jobOfferService.update(job);
		} catch(ConstraintViolationException e) {
			return "redirect:/company/joboffer/"+ job.getId() + "?con=true";
		}
		return "redirect:/company/joboffer/" + job.getId();
	}
	
	@PostMapping("/joboffer/create")
	public String joboffercompanycrate(@RequestParam Map<String,String> allParams, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Company company=companyService.update(companyService.findbyUserId(auth.getName()));
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(allParams.get("expiringDate"),formatter);
			jobOfferService.create(allParams.get("region"), allParams.get("province_"), allParams.get("town"),
					allParams.get("position"), allParams.get("description"), allParams.get("contractType")
					, Education.valueOf(allParams.get("minEducationLevel")), allParams.get("minExperience"),
					date, company);
		}
		catch (Exception e){
			return "redirect:/company/listjoboffer";
		}
		List<JobOffer> jobs = jobOfferService.findbyCompanyId(company.getId());
		model.addAttribute("jobs",jobs);
		model.addAttribute("company",company);
		return "company/listjoboffer";
	}
	
	@GetMapping("/joboffer/{jobId}/delete")
	public String jobofferdelete (@PathVariable("jobId") Long jobId, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		JobOffer job = jobOfferService.findbyId(jobId);
		jobOfferService.delete(job);
		Company company=companyService.findbyUserId(auth.getName());
		List<JobOffer> jobs = jobOfferService.findbyCompanyId(company.getId());
		model.addAttribute("jobs",jobs);
		model.addAttribute("company",company);
		return "redirect:/company/listjoboffer";
	}
	
	@GetMapping("/interested/{jobId}")
	public String intrested (@PathVariable("jobId") Long jobId, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Company company=companyService.findbyUserId(auth.getName());
		JobOffer job = jobOfferService.findbyId(jobId);
		Set<Person> candidencies= job.getCandidancies();
		LocalDateTime now = LocalDateTime.now();
		int now_year= now.getYear(); 
		model.addAttribute("now_year", now_year);
		model.addAttribute("candidencies",candidencies);
		model.addAttribute("company",company);
		return "company/interested";
	}
	
	@GetMapping("/curriculum/{personId}")
	public String curriculum (@PathVariable("personId") Long personId, Model model) {
		Person person = personService.findById(personId);
		Curriculum curriculum = person.getCurriculum();
		model.addAttribute("curriculum", curriculum);
		model.addAttribute("person", person);
		model.addAttribute("email", person.getUser().getEmail());
		return "company/curriculum";
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
	@Autowired
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
}
