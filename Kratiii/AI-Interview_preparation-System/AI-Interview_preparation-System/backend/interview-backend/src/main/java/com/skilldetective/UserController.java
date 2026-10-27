package com.skilldetective;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        userRepository.saveUser(user);

        return "Registration successful!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User loggedInUser = userRepository.loginUser(
                user.getEmail(),
                user.getPassword()
        );

       if (loggedInUser != null) {
    return "Login successful!|" + loggedInUser.getUserId();
}

        return "Invalid email or password!";
    }
}