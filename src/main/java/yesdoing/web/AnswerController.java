package yesdoing.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import yesdoing.domain.Answer;
import yesdoing.domain.AnswerRepository;
import yesdoing.domain.Question;
import yesdoing.domain.QuestionRepository;
import yesdoing.domain.User;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@PostMapping("")
	public String insert(@PathVariable Long questionId, String contents, HttpSession session) {
		User user = (User)session.getAttribute("sessionedUser");
		if(user == null) {
			return "redirect:/users/login";
		}
		
		Question question = questionRepository.findOne(questionId);
		Answer answer = new Answer(user, question, contents);
		answerRepository.save(answer);
		return String.format("redirect:/questions/%d", questionId);
	}
}
