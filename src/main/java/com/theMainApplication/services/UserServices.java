package com.theMainApplication.services;

import com.theMainApplication.dtos.UserDto;
import com.theMainApplication.dtos.request.EmailRequest;
import com.theMainApplication.dtos.request.UserCreationRequest;
import com.theMainApplication.dtos.response.EmailServiceResponse;
import com.theMainApplication.dtos.response.UserServiceOprResponse;
import com.theMainApplication.entities.User;
import com.theMainApplication.exceptions.SuppliersOprException.EmailIdAlreadyExist;
import com.theMainApplication.exceptions.SuppliersOprException.ResourceNotFound;
import com.theMainApplication.exceptions.SuppliersOprException.UserNameAlreadyExist;
import com.theMainApplication.exceptions.SuppliersOprException.UserServiceException;
import com.theMainApplication.mapper.UserModelMapper;
import com.theMainApplication.repositories.UserRepository;
import com.theMainApplication.utils.UserServiceUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    private final UserRepository repository;
    private final WebClient webClient;
    private final UserServiceOprResponse response = new UserServiceOprResponse();
    UserServices(UserRepository repository, WebClient webClient){
        this.repository = repository;
        this.webClient = webClient;
    }

    public List<UserDto> getAllUsers(){
        List<User> users = repository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for(User user : users){
            userDtos.add(UserModelMapper.mapToUserDTO(user));
        }
        return userDtos;
    }

    public UserServiceOprResponse addNewUserV1(UserCreationRequest request){
        try{
            if(UserServiceUtils.isEmailExist(request.getUserEmail(), repository))
                throw new EmailIdAlreadyExist("User already exist with email id!...");
            User user = UserModelMapper.mapToUserV1(request);
            User newUser = repository.save(user);
            EmailRequest emailRequest = new EmailRequest(request.getUserEmail(),
                    request.getUserName(),
                    "Registration",
                    "User Registration Conformation",
                    "http://localhost:8088/userService/approve");

            webClient.post()
                .uri("email/sendEmail")
                .bodyValue(emailRequest)
                .retrieve()
                .bodyToMono(EmailServiceResponse.class)
                .block();

            response.setStatusCode(HttpStatus.CREATED.toString())
                    .setIsOprSuccess(true)
                    .setResponseMsg("User has been added successfully with id : "+ user.getUserId() +"!...");
        }catch (DataIntegrityViolationException exception){
            throw new UserNameAlreadyExist("User already exists!...");
        }

//        Optional<User> userOptional = repository.findByUserName(request.getUserName());
//        if(userOptional.isPresent())
//            throw new UserNameAlreadyExist("User already exists!...");
//        User user = UserModelMapper.mapToUserV1(request);
//        User newUser = repository.save(user);
//        response.setStatusCode(HttpStatus.CREATED.toString())
//                .setIsOprSuccess(true)
//                .setResponseMsg("User has been added successfully with id : "+ user.getUserId() +"!...");

        return response;
    }

    public UserServiceOprResponse addNewUserV2(UserDto userDto){
//        Optional<User> userOptional = repository.findByUserName(userDto.getUserName());
//        if(userOptional.isPresent())
//            throw new UserNameAlreadyExist("User already exists!...");

//        User user = UserModelMapper.mapToUser(userDto);
//        User newUser = repository.save(user);
//        response.setStatusCode(HttpStatus.CREATED.toString())
//                .setIsOprSuccess(true)
//                .setResponseMsg("User has been added successfully with id : "+ user.getUserId() +"!...");
        try{
            User user = UserModelMapper.mapToUser(userDto);
            User newUser = repository.save(user);
            response.setStatusCode(HttpStatus.CREATED.toString())
                    .setIsOprSuccess(true)
                    .setResponseMsg("User has been added successfully with id : "+ user.getUserId() +"!...");
        }catch (DataIntegrityViolationException exception){
            throw new UserNameAlreadyExist("User already exists!...");
        }
        return response;
    }

    public UserServiceOprResponse updateUserDetails(UserDto userDto){

        try {
            Optional<User> userOptional = repository.findByUserName(userDto.getUserName());
            if(userOptional.isEmpty())
                throw new ResourceNotFound("User not found!.");

            User userToBeUpdated = UserModelMapper.mapToUser(userDto);
            userToBeUpdated.setUserName(userOptional.get().getUserName());
            repository.save(userToBeUpdated);
            response.setResponseMsg("User details has been updated successfully!.")
                    .setStatusCode(HttpStatus.ACCEPTED.toString())
                    .setIsOprSuccess(true);

        }catch (RuntimeException exception){
            throw new UserServiceException("Internal Server Error !-> " + exception.getMessage());
        }
        return response;
    }
}
