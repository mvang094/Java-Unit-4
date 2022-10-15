package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.UserDto;
import com.devmountain.noteApp.entities.User;
import com.devmountain.noteApp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<String> addUser(UserDto userDto){
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepo.saveAndFlush(user); //This is where the user gets saved into the database(persisted)
        response.add("User Added Successfully");
        return response;
    }

    @Override
    public List<String> userLogin(UserDto userDto)
    {
        List<String> response = new ArrayList<>();
        //Optionals are there as a way to avoid "NullPointerExceptions" which will break your code
        //and crash application.
        Optional<User> userOptional = userRepo.findByUsername(userDto.getUsername());
        if(userOptional.isPresent())
        {
            if (passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())){
                response.add("User Login Successful");
                response.add(String.valueOf(userOptional.get().getId()));
            }
            else
                response.add("Username or password incorrect");
        }
        else
            response.add("Username or password incorrect");

        return response;
    }

}
