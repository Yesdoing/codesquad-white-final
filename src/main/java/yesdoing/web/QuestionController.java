package yesdoing.web;

import static org.assertj.core.api.Assertions.useRepresentation;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import yesdoing.domain.Question;
import yesdoing.domain.QuestionRepository;
import yesdoing.domain.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@GetMapping("/form")
	public String questionForm(HttpSession session) {
		Object value = session.getAttribute("sessionedUser");
		if(value == null) {
			return "user/login";
		}
		
		
		return "qna/form";
	}
	
	@PostMapping("")
	public String create(Question question, HttpSession session) {
		User user = (User)session.getAttribute("sessionedUser");
		question.setWriter(user);
		questionRepository.save(question);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Long index, Model model) {
		model.addAttribute("question", questionRepository.findOne(index));
		return "qna/show";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable("id") Long index, Model model, HttpSession session) {
		User user = (User)session.getAttribute("sessionedUser");
		if(user == null) {
			return "user/login";
		}
		Question question = questionRepository.findOne(index);
		if(!user.isSameWriter(question.getWriter())) {
			return "qna/failedUpdateQuestion";
		}
		
		model.addAttribute("question", question);
		
		return "qna/updateForm";
	}
	
	@PostMapping("/{id}/form")
	public String update(Question question, HttpSession session) {
		User user = (User)session.getAttribute("sessionedUser");
		question.setWriter(user);
		questionRepository.save(question);
		return "redirect:/";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, HttpSession session) {
		User user = (User)session.getAttribute("sessionedUser");
		if(user == null) {
			return "user/login";
		}
		
		Question question = questionRepository.findOne(id);
		
		if(!user.isSameWriter(question.getWriter())) {
			return "redirect:/"; 
		}
				
		questionRepository.delete(id);
		return "redirect:/";
	}
	
}
