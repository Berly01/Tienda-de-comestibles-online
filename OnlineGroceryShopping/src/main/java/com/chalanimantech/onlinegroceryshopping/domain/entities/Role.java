package com.chalanimantech.onlinegroceryshopping.domain.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {

	private static final long serialVersionUID = 2961571939586638615L;
	private String authority;

    public Role() {
    	super();
    }

    @Override
    @Column(name = "authority", nullable = false)
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
