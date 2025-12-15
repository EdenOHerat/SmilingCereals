package it.uniroma3.siw.smiling_cereals.repo;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.smiling_cereals.model.User;

public interface UserRepo extends CrudRepository<User, Long>{
	
}
