package config;

import model.user.UserRoles;
import model.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.RoleService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users userDB = userService.findUserByUsername(s);

        if(userDB == null) {
            throw new UsernameNotFoundException("Not found user");
        }

        final List<UserRoles> userRoles = roleService.getAllUserRolesPerUser(userDB.getId());

        List<GrantedAuthority> list = new ArrayList<>();

        for (final UserRoles userRole: userRoles) {
            GrantedAuthority grantedAuthority = new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return userRole.getRole().getName();
                }
            };
            list.add(grantedAuthority);
        }

        return new User(userDB.getUsername(), userDB.getPassword(), true, true, true, true, list);
    }
}
