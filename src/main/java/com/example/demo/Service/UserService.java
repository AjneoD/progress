package com.example.demo.Service;

import com.example.demo.Entities.User;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public Optional<User> getUserById(long id) {
        return userRepo.findById(id);
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(User user, long id) throws Exception {
        if (!getUserById(id).isPresent()) {
            throw new Exception("You are trying to update an user that does not exist");
        }
        user.setId(id);
        return userRepo.save(user);
    }

}