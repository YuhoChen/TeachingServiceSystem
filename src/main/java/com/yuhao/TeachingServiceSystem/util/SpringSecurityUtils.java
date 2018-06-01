package com.yuhao.TeachingServiceSystem.util;

import com.yuhao.TeachingServiceSystem.dto.UserDTO;
import com.yuhao.TeachingServiceSystem.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;




public class SpringSecurityUtils {

    public static UserDTO getCurrentUser() {
        Authentication au = SecurityContextHolder.getContext().getAuthentication();
        if (au != null && au.getPrincipal() != null && au.getPrincipal() instanceof UserDTO) {
            UserDTO userDTO = (UserDTO) au.getPrincipal();
            return userDTO;
        }
        return null;
    }

}
