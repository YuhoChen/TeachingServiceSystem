package com.yuhao.TeachingServiceSystem.dto;

import com.yuhao.TeachingServiceSystem.model.Course;


/**
 * DTO 是model的拓展类
 * @author yuhaochen
 * 2018-05-30 17:23:29
 */

public class CourseDTO extends Course{

    private static final long serialVersionUID = 1L;

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
