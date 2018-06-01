package com.yuhao.TeachingServiceSystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.yuhao.TeachingServiceSystem.dto.AuthorityDTO;
import com.yuhao.TeachingServiceSystem.model.Authority;
import com.yuhao.TeachingServiceSystem.util.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;



@Repository
public class AuthorityDAO extends BaseDAO<Authority> {



    public List<Authority> find(AuthorityDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Authority n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getCode())) {
            hql.append(" and n.code = ? ");
            paras.add(dto.getCode());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            hql.append("and n.name like ? ");
            paras.add("%" + dto.getName() + "%");
        }

        return super.find(hql.toString(), paras.toArray(), page);
    }

    public Authority uniqueFind(AuthorityDTO dto) {
        Page page = new Page();
        page.setPageSize(1);
        page.setPage(1);

        List<Authority> list = this.find(dto, page);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public List<Authority> findLikeAuthorities(String name) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Authority n where n.name like ? ");
        paras.add("%" + name + "%");

        return super.find(hql.toString(), paras.toArray(), null);
    }

    public Authority loadByName(String name) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Authority n where n.name = ? ");
        paras.add(name);

        List<Authority> list = super.find(hql.toString(), paras.toArray(), null);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public List<Authority> listTops() {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Authority n where n.parentAuthority is null ");

        return super.find(hql.toString(), paras.toArray(), null);
    }

}
