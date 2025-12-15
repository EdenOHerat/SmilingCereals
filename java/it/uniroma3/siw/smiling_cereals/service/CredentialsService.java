package it.uniroma3.siw.smiling_cereals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.smiling_cereals.model.Credentials;
import it.uniroma3.siw.smiling_cereals.repo.CredentialsRepo;

@Service
public class CredentialsService {
	
	@Autowired
	private CredentialsRepo cr;
	
	public Credentials getCredentialsById(Long id) {
		return cr.findById(id).get();
	}
	
	public Credentials getCredentialsByUsername(String username) {
		return cr.findByUsername(username).get();
	}
	
	public Credentials saveCredentials(Credentials credentials) {
		return cr.save(credentials);
	}
}
