package com.yuhao.TeachingServiceSystem.dto;

import com.yuhao.TeachingServiceSystem.model.Menu;

import java.util.ArrayList;
import java.util.List;


public class MenuDTO extends Menu{

    private List<MenuDTO> children;

    public List<MenuDTO> getChildren() {
        return children;
    }
    public void setChildren(List<MenuDTO> children) {
        this.children = children;
    }

    public void addChild(MenuDTO child){
        if (children==null){
            children=new ArrayList<MenuDTO>();
        }
        children.add(child);
    }






}
