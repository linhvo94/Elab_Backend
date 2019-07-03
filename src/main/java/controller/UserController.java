package controller;


import model.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.UserService;

@RestController
@RequestMapping("/account")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "create-student-account", method = RequestMethod.POST)
    public void createStudentAccount(@RequestBody Users userReq) {
        userService.createStudentAccount(userReq);
    }

    @RequestMapping(path = "create-lecturer-account", method = RequestMethod.POST)
    public void createLecturerAccount(@RequestBody Users userReq) {
        userService.createLecturerAccount(userReq);
    }

    @RequestMapping(path = "create-admin-account", method = RequestMethod.POST)
    public void createAdminAccount(@RequestBody Users userReq) {
        userService.createAdminAccount(userReq);
    }

    @RequestMapping(path = "update-user", method = RequestMethod.PUT)
    public void updateUser(@RequestBody Users userReq) {
        userService.updateUser(userReq);
    }

    @RequestMapping(path = "delete-user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
