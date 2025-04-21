package com.Expense_Management_System.Service;

import com.Expense_Management_System.Entity.User;
import com.Expense_Management_System.Payload.LoginDto;
import com.Expense_Management_System.Payload.UserDto;
import com.Expense_Management_System.Repository.UserRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private JwtService jwtService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, JwtService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
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
        user.setRole("ROLE_USER");
        User save = userRepository.save(user);
        return MapToDto(save);
    }
    public UserDto createManagerAccount(UserDto dto) {
        User user = MapToEntity(dto);
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10)));
        user.setRole("ROLE_MANAGER");
        User save = userRepository.save(user);
        return MapToDto(save);
    }


    public String verfiyLogin(LoginDto loginDto) {
        Optional<User> byEmail = userRepository.findByEmail(loginDto.getEmail());
        if (byEmail.isPresent()){
            User user = byEmail.get();
            if (BCrypt.checkpw(loginDto.getPassword(),user.getPassword())){
                String generateToken = jwtService.generateToken(loginDto.getEmail());
                return generateToken;
            }else{
                return null;
            }
        }
        return null;
    }
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public UserDto patchUser(long id, UserDto dto) {
        Optional<User> byId = userRepository.findById(id);
        User user = byId.get();
        if (dto.getName() != null) {
            user.setName(dto.getName());
        }
        if (dto.getMobile() != null) {
            user.setMobile(dto.getMobile());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null) {
            user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(10)));
        }
        User saved = userRepository.save(user);
        return MapToDto(saved);
    }

    public List<UserDto> getuser() {
        List<User> all = userRepository.findAll();
        List<UserDto> collect = all.stream().map(e -> MapToDto(e)).collect(Collectors.toList());
        return collect;
    }

    public UserDto findUserById(long userId) {
        Optional<User> byId = userRepository.findById(userId);
        User user = byId.get();
        return MapToDto(user);
    }


}
