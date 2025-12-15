package it.uniroma3.siw.smiling_cereals.service;

import org.springframework.beans.factory.annotation.Autowired;
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
}
