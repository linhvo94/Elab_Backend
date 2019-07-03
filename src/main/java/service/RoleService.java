package service;

import model.user.Roles;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class RoleService {

    @Autowired
    SessionFactory sessionFactory;

    public void createNewRole(Roles roleReq) {
        sessionFactory.getCurrentSession().save(roleReq);
    }

}
