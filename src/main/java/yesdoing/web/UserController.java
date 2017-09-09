package yesdoing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
