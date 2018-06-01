package com.yuhao.TeachingServiceSystem.service;

import java.util.ArrayList;
import java.util.List;

import com.yuhao.TeachingServiceSystem.dao.RoleDAO;
import com.yuhao.TeachingServiceSystem.dto.RoleDTO;
import com.yuhao.TeachingServiceSystem.exception.BizException;
import com.yuhao.TeachingServiceSystem.model.Role;
import com.yuhao.TeachingServiceSystem.util.BeanMapper;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.util.ValidationUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class RoleService extends BaseService{

    @Autowired
    private RoleDAO roleDAO;

    public List<RoleDTO> list(RoleDTO dto, Page page) {
        List<Role> models = roleDAO.find(dto, page);
        List<RoleDTO> dtos = toDTOs(models,RoleDTO.class);
        return dtos;
    }

    public RoleDTO findOne(RoleDTO query) {
        Role model = roleDAO.uniqueFind(query);
        return toDTO(model,RoleDTO.class);
    }


    public RoleDTO load(Long id) {
        Role model = roleDAO.load(id);
        return toDTO(model,RoleDTO.class);
    }

    public void save(RoleDTO dto) {
        Role model = new Role();
        toModel(model, dto);
        ValidationUtils.assertTrue(StringUtils.isNotBlank(model.getName()), "角色名不能为空");

        roleDAO.create(model);
    }

    public void update(RoleDTO dto) {
        Role model = roleDAO.load(dto.getId());
        if (model.getEditAble() != null && !model.getEditAble()) {
            throw new BizException("当前角色不可修改");
        }

        toModel(model, dto);

        roleDAO.update(model);
    }

    public void updateAllField(RoleDTO dto) {
        Role model = roleDAO.load(dto.getId());
        toModel(model, dto);
        roleDAO.update(model);
    }

    public void deleteByIds(Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                if (id == 1) {
                    throw new BizException("禁止删除管理员角色");
                }
                roleDAO.deleteById(id);
            }
        }
    }

    public void deleteById(Long id) {
        roleDAO.deleteById(id);
    }

    public RoleDTO uniqueFind(RoleDTO dto) {
        Role model = roleDAO.uniqueFind(dto);
        return toDTO(model,RoleDTO.class);
    }


}
