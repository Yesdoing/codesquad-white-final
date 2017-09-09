package yesdoing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Long index, Model model) {
		model.addAttribute("question", questionRepository.findOne(index));
		return "qna/show";
	}
	
}
