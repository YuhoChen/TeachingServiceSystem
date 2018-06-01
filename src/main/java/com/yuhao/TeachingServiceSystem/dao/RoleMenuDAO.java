package com.yuhao.TeachingServiceSystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.yuhao.TeachingServiceSystem.dto.RoleMenuDTO;
import com.yuhao.TeachingServiceSystem.model.RoleMenu;
import com.yuhao.TeachingServiceSystem.util.Page;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;



@Repository
public class RoleMenuDAO extends BaseDAO<RoleMenu> {

    public List<RoleMenu> find(RoleMenuDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From RoleMenu n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }

        if (dto.getRole() != null) {
            if (dto.getRole().getId() != null) {
                hql.append(" and n.role.id = ? ");
                paras.add(dto.getRole().getId());
            }
        }
        if (dto.getMenu()!=null){
            if (dto.getMenu().getId()!=null){
                hql.append(" and n.menu.id = ? ");
                paras.add(dto.getMenu().getId());
            }
        }

        hql.append(" order by n.id desc ");
        return super.find(hql.toString(), paras.toArray(), page);
    }

    public RoleMenu findOne(RoleMenuDTO dto) {
        Page page = new Page();
        page.setPageSize(1);
        page.setPage(1);

        List<RoleMenu> list = this.find(dto, page);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }



    public void deleteByRole(Long roleId) {
        super.executeUpdate("delete from RoleMenu where role.id = ?", new Object[] {roleId});
    }
}
