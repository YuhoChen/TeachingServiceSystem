package com.yuhao.TeachingServiceSystem.dto;

import com.yuhao.TeachingServiceSystem.model.Role;

public class RoleDTO extends Role{

    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
