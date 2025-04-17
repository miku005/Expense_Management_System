package com.Expense_Management_System.Controller;

import com.Expense_Management_System.Entity.User;
import com.Expense_Management_System.Payload.UserDto;
import com.Expense_Management_System.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
@PostMapping("/sign-in")
    public ResponseEntity<?> createUser(@RequestBody UserDto dto){
    UserDto byEmail = userService.findByEmail(dto.getEmail());
    if (byEmail!=null){
        return new ResponseEntity<>("Email is already exists", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    UserDto byMobile = userService.findByMobile(dto.getMobile());
    if (byMobile!=null){
        return new ResponseEntity<>("Mobile Number already exists",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    UserDto savedUser = userService.addUser(dto);
    return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
}
}
