package com.yuhao.TeachingServiceSystem.dto;

import com.yuhao.TeachingServiceSystem.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public class UserDTO extends User implements UserDetails,BaseDTO{



    private String searchTerm;

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public static ArrayList<Field> getFieldList(){

           ArrayList<Field> fieldList = new ArrayList<Field>() ;
           Class tempClass = UserDTO.class;
           while (tempClass!=null) {//当父类为null的时候说明到达了最上层的父类(Object类).
               fieldList.addAll(Arrays.asList(tempClass .getDeclaredFields()));
               tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
           }
           return fieldList;
       }



    private Collection<? extends GrantedAuthority> authorities;


    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
