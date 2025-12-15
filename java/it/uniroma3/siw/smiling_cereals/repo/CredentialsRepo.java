package it.uniroma3.siw.smiling_cereals.repo;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.smiling_cereals.model.Credentials;

public interface CredentialsRepo extends CrudRepository<Credentials, Long>{
	
	public Optional<Credentials> findByUsername(String username);
}
