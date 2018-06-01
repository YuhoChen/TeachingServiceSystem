package com.yuhao.TeachingServiceSystem.dto;

import com.yuhao.TeachingServiceSystem.model.Authority;


public class AuthorityDTO extends Authority{


    private boolean contains;

    public void setContains(boolean contains) {
        this.contains = contains;
    }

    public boolean isContains() {
        return contains;

    }

}
