package ru.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.community.database.repos.avtoritis_repo;
import ru.community.database.repos.customUser_repo;
import ru.community.database.units.avtoriti;
import ru.community.database.units.customUser;

//Контроллер создания нового пользователя в бд
//Controller for creating new user in DB
@Controller
public class RegistrationController{
	@Autowired
	public void setCur(customUser_repo cur) {this.cur = cur;}
	private static customUser_repo cur;
	@Autowired
	public void setAvtoritis_repo(avtoritis_repo c) {
		avtrepo=c;
	}
	public static avtoritis_repo avtrepo;
	//Форма регистрации
	//Register form
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(){
		return "register-page";
	}
	//создание нового пользователя в бд | нужно добавить защиту от sql-injection
	//Creating new user in db | to-do sql-injection-protection
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerNewUser(
			@RequestParam(value="username") String username,
			@RequestParam(value="password") String password,
			@RequestParam(value="visiblename") String visiblename,
			Model model) {
			try {
				createNewUser(username,password,visiblename);
			} catch (Exception e) {
				model.addAttribute("msg","something went wrong");
				return "register-page";
			}
		return "home";
	}
	//метод создания юзера в бд, но вынесен из пост-метода + тут происходит шифрование пароля
	//method creating user in db + crypting password
	private static void createNewUser(String username, String password, String visiblename) throws Exception {
		if (cur.findByUsername(username)!=null ) {throw new Exception();}
		else {
		String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt("$2a",5));
		customUser a = new customUser(username,pw_hash,true,visiblename);
		avtoriti b = new avtoriti(username, "ROLE_USER");
		avtoriti c = new avtoriti(username, "USER");
		cur.save(a);
		avtrepo.save(b);
		avtrepo.save(c);
		}
	}
}