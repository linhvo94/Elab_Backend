package controller;

import model.user.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.RoleService;


@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(path = "create-role", method = RequestMethod.POST)
    public void createNewRole(Roles roleReq) {
        roleService.createNewRole(roleReq);
    }
}
