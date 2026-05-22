package com.theMainApplication.utils;

import com.theMainApplication.entities.User;
import com.theMainApplication.repositories.UserRepository;

import java.util.Optional;

public class UserServiceUtils {
    public static boolean isUserExist(String userName, UserRepository userRepository){
        Optional<User> userOptional = userRepository.findByUserName(userName);
        return userOptional.isPresent();
    }

    public static boolean isEmailExist(String userEmail, UserRepository repository){
        Optional<User> userOptional = repository.findByUserEmailId(userEmail);
        return userOptional.isPresent();
    }

    public static Optional<User> getUserDetailsWithId(Long userId, UserRepository userRepository){
        return userRepository.findById(userId);
    }
}
