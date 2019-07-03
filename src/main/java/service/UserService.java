package service;

import model.user.Roles;
import model.user.UserRoles;
import model.user.Users;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserService {

    @Autowired
    SessionFactory sessionFactory;

    public Users createBaseAccount(Users userReq) {
        Users user = new Users();

        user.setFirstName(userReq.getFirstName());
        user.setLastName(userReq.getLastName());
        user.setEmail(userReq.getEmail());
        user.setUsername(userReq.getUsername());
        user.setPassword(userReq.getPassword());
        user.setDob(userReq.getDob());

        return user;
    }

    public void createStudentAccount(Users userReq) {
        Users student = createBaseAccount(userReq);

        UserRoles userRoles = new UserRoles();
        userRoles.setRole(getRoleByName("ROLE_STUDENT"));
        student.getUserRoles().add(userRoles);

        sessionFactory.getCurrentSession().save(student);
    }

    public void createLecturerAccount(Users userReq) {
        Users lecturer = createBaseAccount(userReq);

        UserRoles userRoles = new UserRoles();
        userRoles.setRole(getRoleByName("ROLE_LECTURER"));
        lecturer.getUserRoles().add(userRoles);

        sessionFactory.getCurrentSession().save(lecturer);
    }

    public void createAdminAccount(Users userReq) {
        Users admin = createBaseAccount(userReq);

        UserRoles userRoles = new UserRoles();
        userRoles.setRole(getRoleByName("ROLE_ADMIN"));
        admin.getUserRoles().add(userRoles);

        sessionFactory.getCurrentSession().save(admin);
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
