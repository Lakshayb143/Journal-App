package com.loopbreaker.JApp.servies;

import com.loopbreaker.JApp.entity.User;
import com.loopbreaker.JApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{

    @Autowired
    private UserRepository userRepository;


    public void saveEntry(User user){
        userRepository.save(user);
    }


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getByUsername(String username){
        return userRepository.findByUserName(username);
    }

    public Optional<User> getEntryById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteByid(ObjectId id){
        userRepository.deleteById(id);
    }


}
