package com.yuhao.TeachingServiceSystem.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "authority")
public class Authority extends BaseModel{
    private Long id;

    private Authority parentAuthority;
    private String name;
    private String code;
    private String module;


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
    @Column(name = "parent_authority_id", nullable = true )
    public Authority getParentAuthority() {
        return parentAuthority;
    }

    public void setParentAuthority(Authority parentAuthority) {
        this.parentAuthority = parentAuthority;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "code", nullable = true, length = 100)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "module", nullable = true, length = 100)
    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }


}
