package com.example.demo.Service;

import com.example.demo.Entities.User;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo usersRepo;

    public Optional<User> getUserById(long id) {
        return usersRepo.findById(id);
    }

    public User updateUserById(long id, String name, String surname, String email) throws Exception {
        Optional<User> user = usersRepo.findById(id);

        if (!user.isPresent()) {
            throw new Exception("This user does not exists");
        }

        if (name != null) {
            user.get().setName(name);
        }
        if (surname != null) {
            user.get().setSurname(surname);
        }
        if (email != null) {
            user.get().setEmail(email);
        }

        return usersRepo.save(user.get());
    }

    public User createUser(User user) {
        return usersRepo.save(user);
    }



}