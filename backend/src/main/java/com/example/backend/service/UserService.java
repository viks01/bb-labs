package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User authenticateUser(String identifier, String password) {
        Optional<User> userOpt = userRepository.findByUsernameOrEmail(identifier, identifier);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // if (passwordEncoder.matches(password, user.getPassword())) {
            //     return user;
            // }
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public String generateToken(User user) {
        // Implement your token generation logic here (e.g., JWT)
        return "dummy-token-for-" + user.getUsername();
    }
}
