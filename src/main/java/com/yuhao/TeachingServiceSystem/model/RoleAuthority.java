package com.yuhao.TeachingServiceSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "role_authority")
public class RoleAuthority extends BaseModel{
    private Long id;
    private Long roleId;
    private Long authorityId;


    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_id", nullable = false)
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "authority_id", nullable = false)
    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }


}
