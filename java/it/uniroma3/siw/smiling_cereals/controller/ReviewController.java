package it.uniroma3.siw.smiling_cereals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.smiling_cereals.model.Review;
import it.uniroma3.siw.smiling_cereals.service.ReviewService;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewService rs;
	
	@PostMapping("/review/delete")
	public String deleteReview(@RequestParam Long idReview) {
		Review r = rs.getReviewById(idReview);
		rs.deleteReview(r);
		return "redirect:/profile";
	}
}
