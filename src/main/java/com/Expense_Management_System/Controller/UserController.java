package com.Expense_Management_System.Controller;

import com.Expense_Management_System.Payload.LoginDto;
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
    public ResponseEntity<?> registration(@RequestBody UserDto dto) throws Exception {
        UserDto byUsername = userService.findByUsername(dto.getUsername());
        if (byUsername != null) {
            return new ResponseEntity<>("Username is already registered!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UserDto byEmail = userService.findByEmail(dto.getEmail());
        if (byEmail != null) {
            return new ResponseEntity<>("Email is already registered!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UserDto byMobile = userService.findByMobile(dto.getMobile());
        if (byMobile != null) {
            return new ResponseEntity<>("Mobile number is already registered!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UserDto registration = userService.registration(dto);
        return new ResponseEntity<>(registration, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws Exception {
        boolean val = userService.login(loginDto);
        if (val) {
            return new ResponseEntity<>("logged in", HttpStatus.OK);
        }
            return new ResponseEntity<>("Invalid username or password", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
