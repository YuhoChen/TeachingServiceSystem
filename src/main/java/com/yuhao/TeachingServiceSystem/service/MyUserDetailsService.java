package com.yuhao.TeachingServiceSystem.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yuhao.TeachingServiceSystem.dao.MenuDAO;
import com.yuhao.TeachingServiceSystem.dao.UserDAO;
import com.yuhao.TeachingServiceSystem.dto.UserDTO;
import com.yuhao.TeachingServiceSystem.model.User;
import com.yuhao.TeachingServiceSystem.util.BeanMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
public class MyUserDetailsService extends BaseService implements UserDetailsService, Serializable {
    private static Logger logger = Logger.getLogger(MyUserDetailsService.class);
    private static final long serialVersionUID = 1L;


    public static String PREFIX = "ROLE_";


    @Autowired
    private UserDAO userDAO;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return doLoad(username);
        } catch (Exception e) {
            logger.error(e, e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("serial")
    private UserDetails doLoad(String username){

           User user=userDAO.loadUserByUsername(username);

           if (user==null){

               throw new UsernameNotFoundException("Can't find user by username [" + username + "]");
            }

        UserDTO userDTO=toDTO(user,UserDTO.class);

        // 最终用户拥有的所有权限
        List<GrantedAuthority> finalAuthorities = new ArrayList<GrantedAuthority>();


        finalAuthorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        });
        finalAuthorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_user.add";
            }
        });

        userDTO.setAuthorities(finalAuthorities);

        return userDTO;

    }


}
