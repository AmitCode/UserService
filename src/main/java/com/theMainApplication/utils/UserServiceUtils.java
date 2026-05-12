package com.theMainApplication.utils;

import com.theMainApplication.entities.User;
import com.theMainApplication.repositories.UserRepository;

import java.util.Optional;

public class UserServiceUtils {
    public static boolean isUserExist(String userName, UserRepository userRepository){
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if(userOptional.isPresent())
                return true;
        return false;
    }

    public static boolean isEmailExist(String userEmail, UserRepository repository){
        Optional<User> userOptional = repository.findByUserEmailId(userEmail);
        if(userOptional.isPresent())
            return true;
        return false;
    }
}
