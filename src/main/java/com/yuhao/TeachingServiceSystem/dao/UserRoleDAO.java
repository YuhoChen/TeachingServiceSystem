package com.yuhao.TeachingServiceSystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.yuhao.TeachingServiceSystem.dto.UserDTO;
import com.yuhao.TeachingServiceSystem.dto.UserRoleDTO;
import com.yuhao.TeachingServiceSystem.model.User;
import com.yuhao.TeachingServiceSystem.model.UserRole;
import com.yuhao.TeachingServiceSystem.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;


@Repository
public class UserRoleDAO extends BaseDAO<UserRole> {

    public List<UserRole> findByUserId(Long userId) {

        UserRoleDTO query=new UserRoleDTO();
        query.setUserId(userId);
        List<UserRole> userRoleList=find(query,null);

        if (userId==null)
            userRoleList.clear();

        return userRoleList;

    }


    public List<UserRole> find(UserRoleDTO dto, Page page) {
        
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From UserRole n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (dto.getRoleId()!=null) {
            hql.append(" and n.roleId = ? ");
            paras.add(dto.getRoleId());
        }
        if(dto.getUserId()!=null){
            hql.append(" and n.userId = ? ");
            paras.add(dto.getUserId());
        }

        return super.find(hql.toString(), paras.toArray(), page);

    }


    public void deleteByUserId(Long userId) {
        String hql = "delete from UserRole where userId = ?";
        super.executeUpdate(hql, new Object[] {userId});
    }
}
