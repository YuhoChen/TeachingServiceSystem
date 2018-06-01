package com.yuhao.TeachingServiceSystem.service;

import java.util.List;

import com.yuhao.TeachingServiceSystem.dao.RoleDAO;
import com.yuhao.TeachingServiceSystem.dao.UserRoleDAO;
import com.yuhao.TeachingServiceSystem.dto.RoleDTO;
import com.yuhao.TeachingServiceSystem.model.Role;
import com.yuhao.TeachingServiceSystem.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;


@Service
public class UserRoleService extends BaseService{

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private RoleDAO roleDAO;

    public List<RoleDTO> findRolesByUserId(Long userId){
        List<UserRole> userRoles = userRoleDAO.findByUserId(userId);
        List<Role> roles = Lists.newArrayList();
        for (UserRole userRole : userRoles) {
            roles.add(roleDAO.load(userRole.getRoleId()));
        }

        return toDTOs(roles,RoleDTO.class);
    }










    public void save(Long[] ids, Long userId) {
        if (ids != null && userId != null) {
            userRoleDAO.deleteByUserId(userId);
            for (Long roleId : ids) {
                UserRole model = new UserRole();
                model.setRoleId(roleId);
                model.setUserId(userId);
                userRoleDAO.create(model);
            }
        }
    }

}
