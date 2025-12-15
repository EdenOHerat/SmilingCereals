package it.uniroma3.siw.smiling_cereals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.smiling_cereals.model.Review;
import it.uniroma3.siw.smiling_cereals.repo.ReviewRepo;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepo rr;
	
	public void saveReview(Review review) {
		rr.save(review);
	}
	
}
