package com.chalanimantech.onlinegroceryshopping.configuration;

import com.chalanimantech.onlinegroceryshopping.domain.entities.Role;
import com.chalanimantech.onlinegroceryshopping.repository.UserRoleRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.chalanimantech.onlinegroceryshopping.util.constants.ValidationErrorMessages.ROOT_ADMIN;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ValidationErrorMessages.ROLE_ADMIN;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ValidationErrorMessages.ROLE_MODERATOR;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ValidationErrorMessages.ROLE_USER;

@Component
public class DatabaseRolesSeeder {

    private final UserRoleRepository userRoleRepository;

    public DatabaseRolesSeeder(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @PostConstruct
    public void seed() {
        if (this.userRoleRepository.count() == 0) {
        	
            Role rootAdmin = new Role();
            rootAdmin.setAuthority(ROOT_ADMIN);
            Role admin = new Role();
            admin.setAuthority(ROLE_ADMIN);
            Role moderator = new Role();
            moderator.setAuthority(ROLE_MODERATOR);
            Role user = new Role();
            user.setAuthority(ROLE_USER);

            this.userRoleRepository.save(rootAdmin);
            this.userRoleRepository.save(admin);
            this.userRoleRepository.save(moderator);
            this.userRoleRepository.save(user);
        }
    }
}
