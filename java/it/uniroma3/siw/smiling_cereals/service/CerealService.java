package it.uniroma3.siw.smiling_cereals.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.smiling_cereals.model.Cereal;
import it.uniroma3.siw.smiling_cereals.repo.CerealRepo;

@Service
public class CerealService {
	
	@Autowired
	CerealRepo cr;
	
	public Cereal getCerealById(Long id) {
		return cr.findById(id).get();
	}
	
	public Iterable<Cereal> getAllCereals() {
		return cr.findAll();
	}
	
	public void saveCereal(Cereal cereal) {
		cr.save(cereal);
	}
	
	public Iterable<Cereal> getFeaturedCereals() {
		return cr.findByFeaturedTrue();
	}
	
	public List<Cereal> getTopFiveCereals() {
		return cr.findBySmilesGreaterThanEqual(4.5f, PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "smiles"))).getContent();
	}
	
	public void deleteCereal(Cereal cereal) {
		cr.delete(cereal);
	}
}
