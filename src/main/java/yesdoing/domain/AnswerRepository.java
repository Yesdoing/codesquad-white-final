package yesdoing.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
	public Answer findByQuestionId(Long question_id);
}
