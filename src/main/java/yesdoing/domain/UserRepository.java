package yesdoing.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
	public User findByUserId(String userId);
}
