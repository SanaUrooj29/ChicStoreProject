package com.ChicStore.config.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ChicStore.Project.entity.User;
import com.ChicStore.config.JwdProvider;
import com.ChicStore.config.Dao.UserDao;
import com.ChicStore.config.exception.UserException;
import com.ChicStore.config.request.LoginResquest;
import com.ChicStore.config.response.AuthResponse;
import com.ChicStore.config.services.CustomeUserServiceImplementation;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private UserDao userDao;
	private JwdProvider jwtprovider;
	private PasswordEncoder passwordEncoder;
	private CustomeUserServiceImplementation cusi;

	public AuthController(UserDao userDao, PasswordEncoder passwordEncoder, CustomeUserServiceImplementation cusi,
			JwdProvider jwtprovider) {
		super();
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
		this.cusi = cusi;
		this.jwtprovider = jwtprovider;
	}

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {

		String email = user.getEmail();
		String password = user.getPassword();
		String firstString = user.getFirstName();
		String lastNString = user.getLastName();

		User isEmailExist = userDao.findByEmail(email);

		if (isEmailExist != null) {
			throw new UserException("Email is already in use with an account");
		}

		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstString);
		createdUser.setLastName(lastNString);

		User savedUser = userDao.save(createdUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		;

		String token = jwtprovider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse(token, "Signup Success");

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginResquest loginRequest) {
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtprovider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse(token, "Signin Success");

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);

	}

	private Authentication authenticate(String uname, String pass) {
		UserDetails userDetails = cusi.loadUserByUsername(uname);

		if (userDetails == null) {
			throw new BadCredentialsException("Invalid Username");
		}

		if (!passwordEncoder.matches(pass, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password..");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
