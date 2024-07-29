package com.chalanimantech.onlinegroceryshopping.domain.entities;

import javax.persistence.Column;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.*;

@MappedSuperclass
public abstract class BaseEntity {
	
    private String id;

    protected BaseEntity() {}

    @Id
    @GeneratedValue(generator = UUID_STRING)
    @GenericGenerator(name = UUID_STRING, strategy = UUID_GENERATOR)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
