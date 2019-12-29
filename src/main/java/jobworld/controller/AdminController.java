package jobworld.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import jobworld.model.entities.Person;
import jobworld.model.entities.User;
import jobworld.model.entities.JobOffer.Education;
import jobworld.services.CompanyService;
import jobworld.services.JobOfferService;
import jobworld.services.PersonService;
import jobworld.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private JobOfferService jobOfferService;
	private CompanyService companyService;
	private UserService userService;
	private PersonService personService;
	//private static String UPLOADED_FOLDER ="C:/Users/giusp/git/JobWorldAdvanceProgramming/WebContent/resources/img/companies/";
	private static String UPLOADED_FOLDER ="C:\\Users\\cicci\\git\\JobWorldAdvanceProgramming_tiles\\WebContent\\resources\\img\\companies\\";
	
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
        company= companyService.update(company);
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
	public String joboffercompany (@PathVariable("jobId") Long jobId, Model model) {
		JobOffer job = jobOfferService.findbyId(jobId);
		model.addAttribute("job",job);
		return "company/editjoboffer";
	}
	
	@PostMapping("/joboffer/{jobId}/update")
	public String joboffercompanyupdate (@PathVariable("jobId") Long jobId, @RequestParam Map<String,String> allParams, Model model) {
		Company company=jobOfferService.findbyId(jobId).getCompany();
		JobOffer job = jobOfferService.update(jobOfferService.findbyId(jobId));
		job.setPosition(allParams.get("position"));
		LocalDate date = LocalDate.parse(allParams.get("expiringDate"));
		job.setExpiringDate(date);
		job.setContractType(allParams.get("contractType"));
		job.setDescription(allParams.get("description"));
		job.setMinEducationLevel(Education.valueOf(allParams.get("minEducationLevel")));
		job.setMinExperience(allParams.get("minExperience"));
		job.setProvince(allParams.get("province_"));
		job.setRegion(allParams.get("region"));
		job.setTown(allParams.get("town"));
		job = jobOfferService.update(job);
		model.addAttribute("company",company);
		model.addAttribute("job",job);
		return "company/editjoboffer";
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
