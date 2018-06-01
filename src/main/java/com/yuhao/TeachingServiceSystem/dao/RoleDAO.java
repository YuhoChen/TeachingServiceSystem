package com.yuhao.TeachingServiceSystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.yuhao.TeachingServiceSystem.dto.RoleDTO;
import com.yuhao.TeachingServiceSystem.model.Role;
import com.yuhao.TeachingServiceSystem.util.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;



@Repository
public class RoleDAO extends BaseDAO<Role> {


    public List<Role> find(RoleDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Role n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            hql.append(" and n.name = ? ");
            paras.add(dto.getName());
        }
        if (StringUtils.isNotBlank(dto.getCode())) {
            hql.append(" and n.code = ? ");
            paras.add(dto.getCode());
        }
        return super.find(hql.toString(), paras.toArray(), page);
    }

    public Role uniqueFind(RoleDTO dto) {
        Page page = new Page();
        page.setPageSize(1);
        page.setPage(1);

        List<Role> list = this.find(dto, page);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
