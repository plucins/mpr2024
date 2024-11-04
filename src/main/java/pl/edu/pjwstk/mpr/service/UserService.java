package pl.edu.pjwstk.mpr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.edu.pjwstk.mpr.model.User;
import pl.edu.pjwstk.mpr.repository.UserRepository;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    public User getUserById(Long id) {
        if (!userRepository.isUserExists(id)) {
            throw new IllegalArgumentException("Could not find User with ID: " + id);
        }
        return userRepository.getUserById(id);
    }

    public void deleteUser(Long id){
        getUserById(id);
        userRepository.deleteUserById(id);
    }

    public User updateUser(Long actualUserId, User updatedUser){
        User actualUser = getUserById(actualUserId);

        if(!actualUser.isActive() == updatedUser.isActive()){
            actualUser.setActive(updatedUser.isActive());
        }

        if(!actualUser.getRole().equals(updatedUser.getRole())){
            actualUser.setRole(updatedUser.getRole());
        }

        return userRepository.updateUser(actualUserId, actualUser);

    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

}
