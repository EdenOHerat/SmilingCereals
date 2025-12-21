package it.uniroma3.siw.smiling_cereals.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	public List<Review> getLatestReviews() {
		Pageable pageable = PageRequest.of(0, 5);
		return rr.findAllByOrderByCreatedAtDesc(pageable).getContent();
	}
	
	public void deleteReview(Review review) {
		rr.delete(review);
	}
	
	public Review getReviewById(Long id) {
		return rr.findById(id).get();
	}
}
