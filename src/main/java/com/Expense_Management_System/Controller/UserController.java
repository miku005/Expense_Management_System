package com.Expense_Management_System.Controller;

import com.Expense_Management_System.Entity.User;
import com.Expense_Management_System.Payload.LoginDto;
import com.Expense_Management_System.Payload.UserDto;
import com.Expense_Management_System.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
@PostMapping("/sign-in")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto dto, BindingResult result) throws Exception{
   if (result.hasErrors()){
       return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
   }
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
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws Exception{
    boolean b = userService.verfiyLogin(loginDto);
    if (b){
        return new ResponseEntity<>("Logged in",HttpStatus.OK);
    }
    return new ResponseEntity<>("Invalid Email/Password",HttpStatus.INTERNAL_SERVER_ERROR);
}
@DeleteMapping
public ResponseEntity<?> deleteUser(@RequestParam long id) throws Exception{
        userService.deleteUser(id);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
}
@PatchMapping
    public ResponseEntity<?> patchUser(@RequestParam long id,@RequestBody UserDto dto) throws Exception{
    UserDto userDto = userService.patchUser(id, dto);
    return new ResponseEntity<>(userDto,HttpStatus.OK);
}
@GetMapping
    public ResponseEntity<List<?>> getUser() throws Exception{
    List<UserDto> getuser = userService.getuser();
    return new ResponseEntity<>(getuser,HttpStatus.OK);
}
@GetMapping("/userId/{userId}")
    public ResponseEntity<?> findUserById(@PathVariable long userId)throws Exception{
    UserDto userById = userService.findUserById(userId);
    return new ResponseEntity<>(userById,HttpStatus.OK);
}
}
