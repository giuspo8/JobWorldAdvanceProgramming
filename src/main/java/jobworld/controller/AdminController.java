package jobworld.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import jobworld.model.entities.Person;
import jobworld.model.entities.User;
import jobworld.model.entities.JobOffer.Education;
import jobworld.services.CompanyService;
import jobworld.services.JobOfferService;
import jobworld.services.PersonService;
import jobworld.services.UserService;
import jobworld.utils.UtilityForController;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private JobOfferService jobOfferService;
	private CompanyService companyService;
	private UserService userService;
	private PersonService personService;
	private PasswordEncoder passwordEncoder;
	private static String UPLOADED_FOLDER ="C:\\Users\\HP\\git\\JobWorldAdvanceProgramming\\WebContent\\resources\\img\\";
	//private static String UPLOADED_FOLDER ="C:\\Users\\cicci\\git\\JobWorldAdvanceProgramming_tiles\\WebContent\\resources\\img\\";
	
	@GetMapping("/listcompany")
	public String listcompany (Model model) {
		List<Company> companys= companyService.findAll();
		model.addAttribute("companys",companys);
		return "admin/listcompany";
	}
	
	@GetMapping("/deletecompany/{companyId}")
	public String deletecompany (@PathVariable("companyId") Long companyId, Model model) {
		Company company=companyService.update(companyService.findbyId(companyId));
		companyService.delete(company);
		List<Company> companys= companyService.findAll();
		model.addAttribute("companys",companys);
		return "redirect:/admin/listcompany";
	}
	
	@GetMapping("/editcompany/{companyId}")
	public String editcompany (@PathVariable("companyId") Long companyId, Model model) {
		Company company= companyService.findbyId(companyId);
		User user= company.getUser();
		model.addAttribute("user",user);
		model.addAttribute("company",company);
		return "admin/editcompany";
	}
	
	@PostMapping("/editcompany/{companyId}/update")
	public String editcompanyupdate (@PathVariable("companyId") Long companyId, @RequestParam("image") MultipartFile image, @RequestParam Map<String,String> allParams, Model model) {
		Company company=companyService.update(companyService.findbyId(companyId));
        company.setName(allParams.get("nome_azienda"));
        try {
        company= companyService.update(company);
        } catch(ConstraintViolationException e) {
        	return "redirect:/admin/editcompany/" + company.getId()+ "?con=true";
        }
		User user =userService.update(company.getUser());
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
	        Path path_disk = Paths.get(UPLOADED_FOLDER + "/companies/" + name_image);
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
        model.addAttribute("user",user);
		model.addAttribute("company", company);
		return "redirect:/admin/editcompany/" + company.getId();
	}
	
	
	@GetMapping("/listjoboffer/{companyId}")
	public String listjobofferscompany (@PathVariable("companyId") Long companyId,Model model) {
		List<JobOffer> jobs = jobOfferService.findbyCompanyId(companyId);
		model.addAttribute("jobs",jobs);
		return "company/listjoboffer";
	}
	
	@GetMapping("/joboffer/{jobId}")
	public String joboffercompany (@RequestParam(value="date", defaultValue = "" , required = false) String date_error,@PathVariable("jobId") Long jobId, Model model) {
		JobOffer job = jobOfferService.findbyId(jobId);
		String date = UtilityForController.localdatetostringdate(job.getExpiringDate());
		model.addAttribute("date_error",date_error);
		model.addAttribute("date", date);
		model.addAttribute("job",job);
		return "company/editjoboffer";
	}
	
	@PostMapping("/joboffer/{jobId}/update")
	public String joboffercompanyupdate (@PathVariable("jobId") Long jobId, @RequestParam Map<String,String> allParams, Model model) {
		JobOffer job = jobOfferService.update(jobOfferService.findbyId(jobId));
		job.setPosition(allParams.get("position"));
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(allParams.get("expiringDate"),formatter);
			job.setExpiringDate(date);
		}
		catch (Exception e){
			return "redirect:/admin/joboffer/"+ job.getId() + "?date=true";
		}
		job.setContractType(allParams.get("contractType"));
		job.setDescription(allParams.get("description"));
		job.setMinEducationLevel(Education.valueOf(allParams.get("minEducationLevel")));
		job.setMinExperience(allParams.get("minExperience"));
		job.setProvince(allParams.get("province_"));
		job.setRegion(allParams.get("region"));
		job.setTown(allParams.get("town"));
		try {
		job = jobOfferService.update(job);
		} catch(ConstraintViolationException e) {
			return "redirect:/admin/joboffer/"+ job.getId() + "?con=true";
		}
		return "redirect:/admin/joboffer/" + job.getId();
	}
	
	@GetMapping("/joboffer/{jobId}/delete")
	public String jobofferdelete (@PathVariable("jobId") Long jobId, Model model) {
		Company company=jobOfferService.findbyId(jobId).getCompany();
		JobOffer job = jobOfferService.findbyId(jobId);
		jobOfferService.delete(job);
		List<JobOffer> jobs = jobOfferService.findbyCompanyId(company.getId());
		model.addAttribute("jobs",jobs);
		model.addAttribute("company",company);
		return "redirect:/admin/listjoboffer/" + company.getId();
	}
	
	@GetMapping("/interested/{jobId}")
	public String intrested (@PathVariable("jobId") Long jobId, Model model) {
		Company company=jobOfferService.findbyId(jobId).getCompany();
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
	public String curriculum (@PathVariable("personId") Long personId,Model model) {
		Person person = personService.findById(personId);
		Curriculum curriculum = person.getCurriculum();
		model.addAttribute("curriculum", curriculum);
		model.addAttribute("person", person);
		model.addAttribute("email", person.getUser().getEmail());
		return "company/curriculum";
	}
	
	@GetMapping("/profile")
	public String profile (@RequestParam(value="error", defaultValue = "", required = false) String error, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.update(userService.findByEmail(auth.getName()));
		model.addAttribute("user", user);
		String errorMassage = null;
		System.out.print(error);
		if (error.equals("password")) {
			errorMassage = "La vecchia password che hai inserito non esiste";
		} else if (error.equals("newpassword")) {
			errorMassage = "Non hai inserito una nuova password";
		} else if (error.equals("ok")) {
			errorMassage = "Il cambio password è andato a buon fine";
		}
		model.addAttribute("errorMassage", errorMassage);
		return "admin/profile";
	}
	
	
	@PostMapping("/update")
	public String profileupdate (@RequestParam Map<String,String> allParams, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.update(userService.findByEmail(auth.getName()));
		if (allParams.get("new_password") != "") {
			if (passwordEncoder.matches(allParams.get("password"), user.getPassword()) ) {
				user.setPassword(userService.encryptPassword(allParams.get("new_password")));
				user = userService.update(user);
				return "redirect:/admin/profile?error=ok";
			} else {
				return "redirect:/admin/profile?error=password";
			}
		} else {
			return "redirect:/admin/profile?error=newpassword";
		}
	}
	
	@GetMapping("/listuser")
	public String listuser (Model model) {
		List<Person> people = personService.findAll();
		LocalDateTime now = LocalDateTime.now();
		int now_year= now.getYear(); 
		model.addAttribute("now_year", now_year);
		model.addAttribute("people",people);
		return "admin/listuser";
	}
	
	@GetMapping("/listuser/{personId}/delete")
	public String userdelete (@PathVariable("personId") Long personId, Model model) {
		Person person= personService.findById(personId);
		personService.delete(person);
		return "redirect:/admin/listuser";
	}
	
	@GetMapping("/listuser/{personId}/edit")
	public String edituser (@RequestParam(value="date", defaultValue = "" , required = false) String date_error,@PathVariable("personId") Long personId, Model model) {
		Person person= personService.findById(personId);
		User user = person.getUser();
		String date = UtilityForController.localdatetostringdate(person.getBirthDate());
		model.addAttribute("date_error",date_error);
		model.addAttribute("date",date);
		model.addAttribute("user",user);
		model.addAttribute("person",person);
		return "user/profile";
	}
	
	@PostMapping("/listuser/{personId}/update")
	public String updateuser (@PathVariable("personId") Long personId, @RequestParam Map<String,String> allParams, @RequestParam("image") MultipartFile image,  Model model) {
		Person person= personService.update(personService.findById(personId));
		User user = userService.update(person.getUser());
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
	        Path path_disk = Paths.get(UPLOADED_FOLDER + "/users/" + name_image);
	        path_image = "/resources/img/users/"+ name_image;
	        System.out.print(path_image);
	        if (path_image != user.getImage()) {
		        Files.write(path_disk, bytes);
		        user.setImage(path_image);
	        } 
		} catch(IOException e) {
			 e.printStackTrace();
		}
        user.setDescription(allParams.get("description"));			
        user = userService.update(user);
        person.setFirstName(allParams.get("firstName"));
        person.setSecondName(allParams.get("secondName"));
        person.setNumber(allParams.get("number"));
        try {
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        	LocalDate birthDate = LocalDate.parse(allParams.get("birthDate"), formatter);
        	person.setBirthDate(birthDate);
		}
		catch (Exception e){
			return "redirect:/admin/listuser/" + person.getId() + "/edit?date=true";
		}
        try {
        person = personService.update(person);
        } catch(ConstraintViolationException e) {
        	return "redirect:/admin/listuser/" + person.getId() + "/edit?con=true";
        }
        model.addAttribute("user",user);
		model.addAttribute("person", person);
		return "redirect:/admin/listuser/" + person.getId() + "/edit";
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
	@Autowired
	public void setPassEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

}
