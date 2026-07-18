package spring.ai.example.spring_ai_demo.service;

// import java.util.Objects;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import spring.ai.example.spring_ai_demo.entity.User;
import spring.ai.example.spring_ai_demo.repository.UserRepository;

@Service
public class UserService {

	private final AuthenticationManager authenticationManager;

	private final JwtService jwtService;

	

	public UserService(AuthenticationManager authenticationManager, JwtService jwtService,
			UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}


	private final UserRepository userRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public User register(User user) {
		// TODO Auto-generated method stub
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public String verify(User user) {
		// TODO Auto-generated method stub
		Authentication authenticate = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
		);
		// var u=userRepository.findByUserName(user.getUserName());
		if(authenticate.isAuthenticated())
			return jwtService.generateToken((user));
		return "failure";
	}

}
