package com.yuhao.TeachingServiceSystem.dto;

import com.yuhao.TeachingServiceSystem.model.Authority;

import java.util.ArrayList;
import java.util.List;



public class ModuleAuthorityDTO {
    private String module;
    private List<AuthorityDTO> authorities = new ArrayList<AuthorityDTO>();
    public String getModule() {
        return module;
    }
    public void setModule(String module) {
        this.module = module;
    }
    public List<AuthorityDTO> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(List<AuthorityDTO> authorities) {
        this.authorities = authorities;
    }


}
