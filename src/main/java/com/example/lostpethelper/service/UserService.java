package com.example.lostpethelper.service;

import com.example.lostpethelper.model.User;
import com.example.lostpethelper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id) //получаем Optional
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public void deleteUserById (Integer id) {
        userRepository.deleteById(id);
        System.out.printf("User with id = %d was deleted%n", id);
    }

    public User updateUserById(Integer id, User updatedUser) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User existingUser = user.get();

            existingUser.setUserID(updatedUser.getUserID());
            existingUser.setName(updatedUser.getName());
            existingUser.setLastname(updatedUser.getLastname());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setRole(updatedUser.getRole());

            return userRepository.save(existingUser);
        } else {
            //todo: заменить на какой-нибудь error и сделать глобальный обработчик ошибок
            throw new NoSuchElementException("User not found");
        }
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
