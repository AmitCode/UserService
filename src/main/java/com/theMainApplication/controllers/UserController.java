package com.theMainApplication.controllers;

import com.theMainApplication.dtos.UserDto;
import com.theMainApplication.dtos.request.UserCreationRequest;
import com.theMainApplication.dtos.response.UserServiceOprResponse;
import com.theMainApplication.services.impl.UserServicesImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usersOpr")
public class UserController {
    private final UserServicesImpl userServices;
    UserController(UserServicesImpl userServices){
        this.userServices = userServices;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userServices.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/v1/addNewUser")
    public ResponseEntity<UserServiceOprResponse> v1AddNewUser(@Valid @RequestBody UserCreationRequest request){
        return new ResponseEntity<>(userServices.addNewUserV1(request), HttpStatus.CREATED);
    }

    @PostMapping("/v2/addNewUser")
    public ResponseEntity<UserServiceOprResponse> v2AddNewUser(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userServices.addNewUserV2(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/updateUserDetails")
    public ResponseEntity<UserServiceOprResponse> updateUserDetails(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userServices.updateUserDetails(userDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<UserServiceOprResponse> deleteUser(@Valid @RequestHeader String userName){
        return userServices.deleteUserByUserName(userName);
    }

    @PatchMapping("/activateOrDeactivateUser")
    public ResponseEntity<UserServiceOprResponse> modifyUserStatus(@RequestHeader String userName,
                                                                          @RequestHeader Boolean isActive){
        return userServices.activateOrDeactivate(userName, isActive);
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<UserServiceOprResponse> updateUserProfile(@PathVariable Long userId,
                                                                    @Valid @RequestBody UserDto userDto){
        return userServices.updateUserProfile(userId, userDto);
    }

}
