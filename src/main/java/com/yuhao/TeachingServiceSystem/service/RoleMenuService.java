package com.yuhao.TeachingServiceSystem.service;

import java.util.ArrayList;
import java.util.List;

import com.yuhao.TeachingServiceSystem.dao.MenuDAO;
import com.yuhao.TeachingServiceSystem.dao.RoleDAO;
import com.yuhao.TeachingServiceSystem.dao.RoleMenuDAO;
import com.yuhao.TeachingServiceSystem.dto.MenuDTO;
import com.yuhao.TeachingServiceSystem.dto.RoleMenuDTO;
import com.yuhao.TeachingServiceSystem.model.Menu;
import com.yuhao.TeachingServiceSystem.model.Role;
import com.yuhao.TeachingServiceSystem.model.RoleMenu;
import com.yuhao.TeachingServiceSystem.util.BeanMapper;
import com.yuhao.TeachingServiceSystem.util.Page;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class RoleMenuService extends BaseService{

    @Autowired
    private RoleMenuDAO roleMenuDAO;
    @Autowired
    private MenuDAO menuDAO;
    @Autowired
    private RoleDAO roleDAO;


    public List<RoleMenuDTO> find(RoleMenuDTO query, Page page) {
        List<RoleMenu> models = roleMenuDAO.find(query, page);
        List<RoleMenuDTO> dtos = toDTOs(models,RoleMenuDTO.class);
        return dtos;
    }

    public List<RoleMenuDTO> findAll() {
        return this.find(new RoleMenuDTO(), null);
    }

    public List<RoleMenuDTO> find(int count) {
        Page page = new Page();
        page.setPage(1);
        page.setPageSize(count);
        return this.find(new RoleMenuDTO(), page);
    }

    public Long create(RoleMenuDTO dto) {
        RoleMenu model = new RoleMenu();
        toModel(model, dto);
        return roleMenuDAO.create(model);
    }

    public RoleMenuDTO load(Long id) {
        RoleMenu model = roleMenuDAO.load(id);
        return toDTO(model,RoleMenuDTO.class);
    }

    public void updateAllFields(RoleMenuDTO dto) {
        RoleMenu model = roleMenuDAO.load(dto.getId());
        toModel(model, dto);
        roleMenuDAO.update(model);
    }

    public void deleteByIds(Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                //throw new RuntimeException("未实现删除逻辑，请确认是物理删除还是逻辑删除");
                roleMenuDAO.deleteById(id);
            }
        }
    }

    public void deleteById(Long id) {
        this.deleteByIds(new Long[] {id});
    }

    public RoleMenuDTO findOne(RoleMenuDTO query) {
        RoleMenu model = roleMenuDAO.findOne(query);
        return toDTO(model,RoleMenuDTO.class);
    }



    private void toModel(RoleMenu model, RoleMenuDTO dto) {
        BeanMapper.copy(dto, model);
    }



    public List<RoleMenuDTO> findByRoleId(Long roleId) {
        RoleMenuDTO query = new RoleMenuDTO();
        query.setRole(new Role());
        query.getRole().setId(roleId);

        return toDTOs(roleMenuDAO.find(query, null),RoleMenuDTO.class);

    }

    public List<MenuDTO> findMenuByRoleId(Long roleId){

        List<RoleMenuDTO> roleMenuDTOList=this.findByRoleId(roleId);

        List<Menu> menuList=new ArrayList<Menu>();

        for (RoleMenuDTO roleMenuDTO:roleMenuDTOList){

            MenuDTO menuDTO=new MenuDTO();
            menuDTO.setId(roleMenuDTO.getMenu().getId());
            menuList.addAll(menuDAO.find(menuDTO,null));
        }

        return toDTOs(menuList,MenuDTO.class);

    }



    @Transactional
    public void update(Long roleId, Long[] menuIds) {
          roleMenuDAO.deleteByRole(roleId);
          Role role=roleDAO.load(roleId);
        for (Long menuId:menuIds) {
            RoleMenu roleMenu=new RoleMenu();
            Menu menu=menuDAO.load(menuId);
            roleMenu.setMenu(menu);
            if (menu.getLevel()==2){
                RoleMenu parentRoleMenu=new RoleMenu();
                parentRoleMenu.setRole(role);
                parentRoleMenu.setMenu(menu.getMenuByParentMenuId());

                if (roleMenuDAO.find(toDTO(parentRoleMenu,RoleMenuDTO.class),null).size()==0)
                    roleMenuDAO.create(parentRoleMenu);

            }
            roleMenu.setRole(role);
            if (roleMenuDAO.find(toDTO(roleMenu,RoleMenuDTO.class),null).size()==0)
                roleMenuDAO.create(roleMenu);
        }

    }
}
