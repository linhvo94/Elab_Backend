package controller;


import model.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import store.UserStore;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserStore userStore;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_STUDENT')")
    @RequestMapping(path = "get-users", method = RequestMethod.GET)
    public List<Users> getAllUsers() {
        return userStore.getAllUsers();
    }

    @RequestMapping(path = "create-student-account", method = RequestMethod.POST)
    public ResponseEntity<?> createStudentAccount(@RequestBody Users userReq) {
        return userStore.createStudentAccount(userReq);
    }

    @RequestMapping(path = "create-lecturer-account", method = RequestMethod.POST)
    public ResponseEntity<?> createLecturerAccount(@RequestBody Users userReq) {
        return userStore.createLecturerAccount(userReq);
    }

    @RequestMapping(path = "create-admin-account", method = RequestMethod.POST)
    public ResponseEntity<?> createAdminAccount(@RequestBody Users userReq) {
        return userStore.createAdminAccount(userReq);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_STUDENT')")
    @RequestMapping(path = "update-user", method = RequestMethod.PUT)
    public void updateUser(@RequestBody Users userReq) {
        userStore.updateUser(userReq);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path = "delete-user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable int id) {
        userStore.deleteUser(id);
    }
}
