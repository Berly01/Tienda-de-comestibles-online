package com.chalanimantech.onlinegroceryshopping.repository;

import com.chalanimantech.onlinegroceryshopping.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<Role, String> {
    Role findByAuthority(String authority);
}
