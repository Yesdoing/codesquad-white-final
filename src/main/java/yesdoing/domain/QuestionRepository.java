package yesdoing.domain;

import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository <Question, Long>{
	public Question findByWriter(String writer);
}
