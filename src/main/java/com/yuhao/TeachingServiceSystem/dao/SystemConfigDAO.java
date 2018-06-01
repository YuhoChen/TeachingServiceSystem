package com.yuhao.TeachingServiceSystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.yuhao.TeachingServiceSystem.dto.SystemConfigDTO;
import com.yuhao.TeachingServiceSystem.model.SystemConfig;
import com.yuhao.TeachingServiceSystem.util.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;



@Repository
public class SystemConfigDAO extends BaseDAO<SystemConfig> {


    public List<SystemConfig> find(SystemConfigDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From SystemConfig n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getKey())) {
            hql.append(" and n.key = ? ");
            paras.add(dto.getKey());
        }

        return super.find(hql.toString(), paras.toArray(), page);
    }

    /**
     * 只找出一条符合条件的记录
     * @param dto
     * @return
     */
    public SystemConfig findOne(SystemConfigDTO dto) {
        Page page = new Page();
        page.setPageSize(1);
        page.setPage(1);

        List<SystemConfig> list = this.find(dto, page);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
