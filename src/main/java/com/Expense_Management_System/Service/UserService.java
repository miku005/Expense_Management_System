package com.Expense_Management_System.Service;

import com.Expense_Management_System.Entity.User;
import com.Expense_Management_System.Payload.UserDto;
import com.Expense_Management_System.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    UserDto MapToDto (User user){
       return modelMapper.map(user,UserDto.class);
    }
    User MapToEntity (UserDto dto){
        return modelMapper.map(dto,User.class);
    }

    public UserDto findByEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()){
            return MapToDto(byEmail.get());
        }
        return null;
    }

    public UserDto findByMobile(String mobile) {
        Optional<User> byMobile = userRepository.findByMobile(mobile);
        if (byMobile.isPresent()){
            return MapToDto(byMobile.get());
        }
        return null;
    }

    public UserDto addUser(UserDto dto) {
        User user = MapToEntity(dto);
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10)));
        User save = userRepository.save(user);
        return MapToDto(save);
    }
}
