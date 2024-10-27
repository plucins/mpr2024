package repository;

import java.util.HashMap;
import java.util.Map;


import model.User;


public class UserRepository implements RepositoryInterface<User> {
    private final Map<Long, User> users = new HashMap<>();

    @Override
    public Map<Long, User> getDataBase() {
        return users;
    }

    public User createUser(User user){
        users.put(RepoUtils.getNextId(this), user);
        return users.get(RepoUtils.getHighestId(this));
    }

    public User getUserById(Long id){
        return users.get(id);
    }

    public boolean isUserExists(Long id){
        return users.containsKey(id);
    }

    public User updateUser(Long actualUserId, User updatedUser){
        users.replace(actualUserId, updatedUser);
        return getUserById(actualUserId);
    }

    public boolean deleteUserById(Long id){
        return users.remove(id) != null;
    }
}
