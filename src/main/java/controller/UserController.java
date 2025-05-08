package controller;

import model.User;
import service.UserService;
import java.util.List;

/**
 * Controlador para la entidad User.
 */
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User createUser(User user) throws Exception {
        return userService.createUser(user);
    }

    public User updateUser(User user) throws Exception {
        return userService.updateUser(user);
    }

    public void deleteUser(int userId) throws Exception {
        userService.deleteUser(userId);
    }

    public User getUserById(int userId) throws Exception {
        return userService.getUserById(userId);
    }

    public List<User> getAllUsers() throws Exception {
        return userService.getAllUsers();
    }
}
