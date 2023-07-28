package cinema.config;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final UserService userService;
    private final RoleService roleService;

    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void inject() {
        roleService.add(new Role(Role.RoleName.ADMIN));
        roleService.add(new Role(Role.RoleName.USER));
        User admin = new User();
        admin.setEmail("admin@cinema.com");
        admin.setPassword("12345678");
        admin.setRoles(Set.of(roleService.getByName("ADMIN")));
        userService.add(admin);
        User user = new User();
        user.setEmail("user@cinema.com");
        user.setPassword("12345678");
        user.setRoles(Set.of(roleService.getByName("USER")));
        userService.add(user);
    }
}
