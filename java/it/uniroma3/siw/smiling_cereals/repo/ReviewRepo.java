package it.uniroma3.siw.smiling_cereals.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.smiling_cereals.model.Review;

public interface ReviewRepo extends CrudRepository<Review, Long>{
	
	Page<Review> findAllByOrderByCreatedAtDesc(Pageable pageable);
	
}
