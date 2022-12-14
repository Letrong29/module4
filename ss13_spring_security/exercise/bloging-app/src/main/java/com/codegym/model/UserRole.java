package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "User_Role", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "USER_ROLE_UK", columnNames = { "User_Id", "Role_Id" }) })
public class UserRole {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "User_Id", nullable = false)
    private com.codegym.model.AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "Role_Id", nullable = false)
    private com.codegym.model.AppRole appRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.codegym.model.AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(com.codegym.model.AppUser appUser) {
        this.appUser = appUser;
    }

    public com.codegym.model.AppRole getAppRole() {
        return appRole;
    }

    public void setAppRole(com.codegym.model.AppRole appRole) {
        this.appRole = appRole;
    }

}
