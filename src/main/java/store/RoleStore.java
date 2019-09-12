package store;

import model.user.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import service.RoleService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class RoleStore {

    @Autowired
    private RoleService roleService;

    public ResponseEntity<String> createNewRole(Roles roleReq) {
        return roleService.createNewRole(roleReq);
    }

    public List<Roles> getAllRoles() {
        return roleService.getAllRoles();
    }
}
