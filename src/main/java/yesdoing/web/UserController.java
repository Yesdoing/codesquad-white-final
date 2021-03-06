package yesdoing.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import yesdoing.domain.User;
import yesdoing.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/login")
	public String login_get() {
		return "user/login";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}
	
	@GetMapping("/form")
	public String join() {
		return "user/form";
	}
	
	@PostMapping("")
	public String joinSave(User user) {
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("/{userId}")
	public String profile(@PathVariable String userId, Model model) {
		model.addAttribute("user", userRepository.findByUserId(userId));
		return "user/profile";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable("id") Long index, Model model, HttpSession session) {
		User user = (User)session.getAttribute("sessionedUser");
		if (user == null) {
			return "redirect:/users/login";
		} 
		
		if(!user.isSameUser(userRepository.findOne(index))) {
			return "redirect:/";
		}
		
		model.addAttribute("user", userRepository.findOne(index));
		return "user/updateForm";
	}
	
	@PostMapping("/{id}/update")
	public String update(User user) {
		System.out.println("updated data");
		User tempUser = userRepository.findOne(user.getId());
		if(tempUser == null) {
			return "redirect:/users";
		}
		
		if(tempUser.getPassword().equals(user.getPassword())) {
			userRepository.save(user);			
		}
		
		return "redirect:/users";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if(!user.passwordIsEquals(password)) {
			return "user/login_failed";
		}
		
		session.setAttribute("sessionedUser", user);
		return "redirect:/";
	}
}
