package service;

import model.user.Roles;
import model.user.UserRoles;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class RoleService {

    @Autowired
    SessionFactory sessionFactory;

    public ResponseEntity<String> createNewRole(Roles roleReq) {
        if(findRoleByName(roleReq.getName()) == null) {
            sessionFactory.getCurrentSession().save(roleReq);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Role " + roleReq.getName()
                    + " already exists", HttpStatus.CONFLICT);
        }
    }

    public List<Roles> getAllRoles() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Roles");
        return query.list();
    }

    public List<UserRoles> getAllUserRolesPerUser(int userID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from UserRoles as ur where ur.user.id = :id");
        query.setInteger("id", userID);
        return query.list();
    }

    public Roles findRoleByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Roles as r where r.name = :name");
        query.setString("name", name);
        return (Roles) query.uniqueResult();
    }

}
