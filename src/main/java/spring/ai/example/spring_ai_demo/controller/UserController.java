/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spring.ai.example.spring_ai_demo.controller;

// import java.util.Objects;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.ai.example.spring_ai_demo.entity.User;
// import spring.ai.example.spring_ai_demo.repository.UserRepository;
import spring.ai.example.spring_ai_demo.service.UserService;

/**
 *
 * @author nanua
 */
@RestController
public class UserController {
	
    // private final UserRepository userRepository;
	private final UserService  userService;

	// public UserController(UserService userService ,UserRepository userRepository) {
	// 	this.userRepository = userRepository;
	// 	this.userService = userService;
	// }


	public UserController(UserService userService) {
		this.userService = userService;
	}


	@PostMapping("/register")
	public User register(@RequestBody User user){
		return userService.register(user);
	}

	@PostMapping("/login")
	public String login(@RequestBody User user){
		return userService.verify(user);
	}
}
