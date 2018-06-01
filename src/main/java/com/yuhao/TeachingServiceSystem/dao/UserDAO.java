package com.yuhao.TeachingServiceSystem.dao;

import com.yuhao.TeachingServiceSystem.dto.UserDTO;
import com.yuhao.TeachingServiceSystem.model.User;
import com.yuhao.TeachingServiceSystem.util.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDAO extends BaseDAO<User>{


    public List<User> find(UserDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From User n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getUsername())) {
            hql.append(" and n.username = ? ");
            paras.add(dto.getUsername());
        }
        if(StringUtils.isNotBlank(dto.getPassword())){
            hql.append(" and n.password = ? ");
            paras.add(dto.getPassword());
        }


        return super.find(hql.toString(), paras.toArray(), page);

    }

    public User findOne(UserDTO dto) {
        Page page = new Page();
        page.setPageSize(1);
        page.setPage(1);

        List<User> list = this.find(dto, page);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }




    @Override
    public void update(User t) {
        super.update(t);
    }


    public User loadUserByUsername(String username) {
        UserDTO query = new UserDTO();
        query.setUsername(username);
        return this.findOne(query);
    }

}
