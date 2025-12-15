package it.uniroma3.siw.smiling_cereals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.smiling_cereals.model.Credentials;
import it.uniroma3.siw.smiling_cereals.model.User;
import it.uniroma3.siw.smiling_cereals.service.CredentialsService;
import it.uniroma3.siw.smiling_cereals.service.UserService;
import jakarta.validation.Valid;
import static it.uniroma3.siw.smiling_cereals.model.Credentials.DEFAULT_ROLE;

@Controller
public class AuthController {
	
	@Autowired
	private CredentialsService credentialsService;
    @Autowired
	private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;  // <-- inject encoder

	
	@GetMapping(value = "/register") 
	public String showRegisterForm (Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newCredentials", new Credentials());
		return "registrationForm";
	}
	
	@GetMapping(value = "/login") 
	public String showLoginForm (Model model) {
		return "loginForm";
	}

	@GetMapping(value = "/") 
	public String index(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
	        return "homepage.html";
		}
		else {		
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.getCredentialsByUsername(userDetails.getUsername());
			if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
				return "admin/homepageAdmin.html";
			}
		}
        return "homepage.html";
	}
		
    @GetMapping(value = "/success")
    public String defaultAfterLogin(Model model) {
        
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentialsByUsername(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "admin/homepageAdmin.html";
        }
        return "homepage.html";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("newUser") User user,
                               BindingResult userBindingResult,
                               @Valid @ModelAttribute("newCredentials") Credentials credentials,
                               BindingResult credentialsBindingResult,
                               Model model) {

        if (!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
            userService.saveUser(user);

            // 🔥 encode raw password before saving
            credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));

            credentials.setUser(user);
            credentials.setRole(DEFAULT_ROLE);
            credentialsService.saveCredentials(credentials);

            model.addAttribute("newUser", user);
            return "registrationSuccessful";
        }
        return "registrationForm";
    }

}
