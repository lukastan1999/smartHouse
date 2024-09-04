package com.example.smartHouse.service;

import com.example.smartHouse.dto.LoginDto;
import com.example.smartHouse.dto.RegistrationRequest;
import com.example.smartHouse.entity.User;
import com.example.smartHouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public User loginUser(LoginDto loginDto) {
        Optional<User> optionalUser = userRepository.findByEmail(loginDto.getEmail());
        if (optionalUser.isPresent()) {
            if (optionalUser.get().isActive()) {
                if (optionalUser.get().getPassword().equals(loginDto.getPassword())) {
                    return optionalUser.get();
                }
            }
        }
        return null;
    }

    public User registerUser(RegistrationRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // Hash the password before saving it
        String hashedPassword = hashPassword(request.getPassword());

        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(request.getRole());
        user.setActive(false);

        // Generate activation token
        String token = UUID.randomUUID().toString();
        user.setActivationToken(token);
        user.setActivationTokenExpiry(LocalDateTime.now().plusHours(24));

        userRepository.save(user);

        // Send activation email
        // NARAVNO DA MEJL NE RADI
//        emailService.sendActivationEmail(user);

        return user;
    }

    private String hashPassword(String password) {
        // Implement password hashing (e.g., BCrypt)
        return password;
    }

    public boolean activateUser(String token) {
        Optional<User> optionalUser = userRepository.findByActivationToken(token);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.isActive()) {
                return false;
            }
            if (user.getActivationTokenExpiry().isBefore(LocalDateTime.now())) {
                return false;
            }

            user.setActive(true);
            user.setActivationToken(null);
            user.setActivationTokenExpiry(null);
            userRepository.save(user);

            return true;
        }

        return false;
    }


    public Boolean addAccommodation(Long userId, Long id) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            List<Long> lista = optionalUser.get().getAccommodationIds();
            lista.add(id);
            optionalUser.get().setAccommodationIds(lista);
            userRepository.save(optionalUser.get());
            return true;
        }
        return false;
    }
}
