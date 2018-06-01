package com.yuhao.TeachingServiceSystem.dao;

import java.util.List;

import com.yuhao.TeachingServiceSystem.model.RoleAuthority;
import org.springframework.stereotype.Repository;


@Repository
public class RoleAuthorityDAO extends BaseDAO<RoleAuthority> {

    public List<RoleAuthority> findByRoleId(Long roleId) {
        String hql = "from RoleAuthority where roleId = ?";
        return find(hql, new Object[] {roleId}, null);
    }

    public void deleteByRoleId(Long roleId) {
        String hql = "delete from RoleAuthority where roleId = ?";
        super.executeUpdate(hql, new Object[] {roleId});
    }

}
