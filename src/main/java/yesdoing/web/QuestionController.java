package yesdoing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import yesdoing.domain.Question;
import yesdoing.domain.QuestionRepository;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@GetMapping("/form")
	public String questionForm() {
		return "qna/form";
	}
	
	@PostMapping("")
	public String create(Question question) {
		questionRepository.save(question);
		return "redirect:/";
	}
	
	
}
