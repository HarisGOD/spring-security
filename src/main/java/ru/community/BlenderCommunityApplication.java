package ru.community;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.community.database.repos.avtoritis_repo;
import ru.community.database.repos.customUser_repo;


//первый контроллер, ни имеет ничего особенного
//... 
@Controller
@SpringBootApplication(scanBasePackageClasses= {RegistrationController.class, PostingController.class })
public class BlenderCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlenderCommunityApplication.class, args);
		
	}
	@Autowired
	public void setCustomUser_repo(customUser_repo c) {
		BlenderCommunityApplication.user_repo = c;
	}
	public static customUser_repo user_repo;
	@Autowired
	public void setAvtoritis_repo(avtoritis_repo c) {
		BlenderCommunityApplication.avtrepo=c;
	}
	public static avtoritis_repo avtrepo;
	//Только для проверки выбранного пользователя
	@RequestMapping(value="/my-profile")
	public String profile(Principal principal, Model model) {
		model.addAttribute("attribute", principal.getName());
		
		return "hello";
		
	}
}
