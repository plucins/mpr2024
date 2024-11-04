package pl.edu.pjwstk.mpr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pjwstk.mpr.model.User;
import pl.edu.pjwstk.mpr.repository.UserRepository;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Could not find User with ID: " + id);
        }
        return userRepository.findById(id).get();
    }

    public void deleteUser(Long id){
        userRepository.delete(getUserById(id));
    }

    public User updateUser(Long actualUserId, User updatedUser){
        User actualUser = getUserById(actualUserId);

        if(!actualUser.isActive() == updatedUser.isActive()){
            actualUser.setActive(updatedUser.isActive());
        }

        if(!actualUser.getRole().equals(updatedUser.getRole())){
            actualUser.setRole(updatedUser.getRole());
        }

        return userRepository.save(actualUser);

    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
