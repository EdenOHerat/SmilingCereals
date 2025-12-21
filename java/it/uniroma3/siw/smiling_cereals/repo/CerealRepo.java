package it.uniroma3.siw.smiling_cereals.repo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.smiling_cereals.model.Cereal;
public interface CerealRepo extends CrudRepository<Cereal, Long>{
	
	public Iterable<Cereal> findByFeaturedTrue();
	
	public Page<Cereal> findBySmilesGreaterThanEqual(Float smiles, Pageable pageable);
}
