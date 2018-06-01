package com.yuhao.TeachingServiceSystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.yuhao.TeachingServiceSystem.dto.MenuDTO;
import com.yuhao.TeachingServiceSystem.model.Menu;
import com.yuhao.TeachingServiceSystem.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;




@Repository
public class MenuDAO extends BaseDAO<Menu> {


    public List<Menu> find(MenuDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Menu n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (dto.getLevel() != null) {
            hql.append(" and n.level = ? ");
            paras.add(dto.getLevel());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            hql.append(" and n.name = ? ");
            paras.add(dto.getName());
        }
        if (StringUtils.isNotBlank(dto.getHref())) {
            hql.append(" and n.href = ? ");
            paras.add(dto.getHref());
        }
        if (dto.getShow() != null) {
            hql.append(" and n.show = ? ");
            paras.add(dto.getShow());
        }

        hql.append(" and n.deleted = 0 ");

        hql.append(" order by n.level asc, n.seq asc ");

        return super.find(hql.toString(), paras.toArray(), page);
    }



}
