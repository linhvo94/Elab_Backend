package store;

import model.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import service.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserStore {

    @Autowired
    private UserService userService;

    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    public ResponseEntity<?> createStudentAccount(Users userReq) {
        return userService.createUserAccount(userReq, "ROLE_STUDENT");
    }

    public ResponseEntity<?> createLecturerAccount(Users userReq) {
        return userService.createUserAccount(userReq, "ROLE_LECTURER");
    }

    public ResponseEntity<?> createAdminAccount(Users userReq) {
        return userService.createUserAccount(userReq,"ROLE_ADMIN");
    }

    public void updateUser(Users userReq) {
        userService.updateUser(userReq);
    }

    public void deleteUser(int id) {
        userService.deleteUser(id);
    }
}
