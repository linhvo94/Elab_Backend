package controller;

import model.user.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.RoleService;
import store.RoleStore;

import java.util.List;

@RestController
@RequestMapping("/")
public class RoleController {

    @Autowired
    private RoleStore roleStore;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path = "create-role", method = RequestMethod.POST)
    public ResponseEntity<String> createNewRole(@RequestBody Roles roleReq) {
        return roleStore.createNewRole(roleReq);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path = "get-roles", method = RequestMethod.GET)
    public List<Roles> getAllRoles() {
        return roleStore.getAllRoles();
    }

}
