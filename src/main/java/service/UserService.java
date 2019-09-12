package service;

import model.user.Roles;
import model.user.UserRoles;
import model.user.Users;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    ResponseService responseService;

    public ResponseEntity<String> validateUserAccount(Users userReq) {
        if(userReq.getUsername() == null) {
            return responseService.handleBadRequest("Username cannot be null.");
        }

        if(userReq.getPassword() == null) {
            return responseService.handleBadRequest("Password cannot be null.");
        }

        if(userReq.getUsername().length() <= 4 ) {
            return responseService.handleBadRequest("Username must be more than 4 characters.");
        }

        if(userReq.getPassword().length() <= 4 ) {
            return responseService.handleBadRequest("Password must be more than 4 characters.");
        }

        if(findUserByUsername(userReq.getUsername()) != null) {
            return responseService.handleDuplicateEntityRequest("Username '" + userReq.getUsername()
                    + "' already exists");
        }

        return null;

    }


    public ResponseEntity<?> createUserAccount(Users userReq, String role) {
        if (validateUserAccount(userReq) == null) {
            Users user = new Users();

            user.setFirstName(userReq.getFirstName());
            user.setLastName(userReq.getLastName());
            user.setEmail(userReq.getEmail());
            user.setUsername(userReq.getUsername());
            user.setPassword(passwordEncoder.encode(userReq.getPassword()));
            user.setDob(userReq.getDob());

            UserRoles userRoles = new UserRoles();
            userRoles.setUser(user);
            userRoles.setRole(getRoleByName(role));
            user.getUserRoles().add(userRoles);


            sessionFactory.getCurrentSession().save(user);
            return ResponseEntity.ok().body(user);

        } else {
            return validateUserAccount(userReq);
        }

    }


    public List<Users> getAllUsers() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Users");
        return query.list();
    }


    public Roles getRoleByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Roles as r where r.name = :name");
        query.setString("name", name);
        return (Roles) query.uniqueResult();
    }

    public Users findUserByUsername(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Users as u where u.username = :username");
        query.setString("username", username);
        return (Users) query.uniqueResult();
    }

    public void updateUser(Users userReq) {
        Users userDB = findUserByID(userReq.getId());
        userDB.setFirstName(userReq.getFirstName());
        userDB.setLastName(userReq.getLastName());
        userDB.setEmail(userReq.getEmail());
        userDB.setDob(userReq.getDob());
        sessionFactory.getCurrentSession().update(userDB);
    }

    public void deleteUser(int id) {
        Users userDB = findUserByID(id);
        sessionFactory.getCurrentSession().delete(userDB);

    }

    public Users findUserByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Users as u where u.id = :id");
        query.setInteger("id", id);
        return (Users) query.uniqueResult();
    }
}
