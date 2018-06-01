package com.yuhao.TeachingServiceSystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yuhao.TeachingServiceSystem.common.MenuLevels;
import com.yuhao.TeachingServiceSystem.dao.MenuDAO;
import com.yuhao.TeachingServiceSystem.dto.MenuDTO;
import com.yuhao.TeachingServiceSystem.model.Menu;
import com.yuhao.TeachingServiceSystem.util.BeanMapper;
import com.yuhao.TeachingServiceSystem.util.Page;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MenuService extends BaseService{


    @Autowired
    private MenuDAO menuDAO;

    public List<MenuDTO> list(MenuDTO dto, Page page) {
        List<Menu> models = menuDAO.find(dto, page);
        List<MenuDTO> dtos = toDTOs(models,MenuDTO.class);
        return dtos;
    }

    public List<MenuDTO> findAllMenu(MenuDTO dto){

        List<MenuDTO> menuDTOList=this.list(dto,null);

        HashMap<Long,MenuDTO> map1=new HashMap<Long, MenuDTO>();//保存一级菜单
        List<MenuDTO> menuDTOList1=new ArrayList<MenuDTO>();
        for (MenuDTO menu:menuDTOList) {                        //找出所有的一级菜单
            if (menu.getLevel().intValue()==1){
                map1.put(menu.getId(),menu);
                menuDTOList1.add(menu);
            }
        }
        for (MenuDTO menu:menuDTOList){                        //找出所有的二级菜单
            if (menu.getLevel().intValue()==2)
                map1.get(menu.getParentMenuId()).addChild(menu);
        }
        return menuDTOList1;
    }



    public MenuDTO findOne(MenuDTO dto) {
        Page page = new Page();
        page.setPageSize(1);
        page.setPage(1);
        List<MenuDTO> list = this.list(dto, page);

        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public MenuDTO getRootMenu(List<MenuDTO> menuDTOList){
        HashMap<Long,MenuDTO> map1=new HashMap<Long, MenuDTO>();//保存一级菜单
        List<MenuDTO> menuDTOList1=new ArrayList<MenuDTO>();
        for (MenuDTO menu:menuDTOList) {                        //找出所有的一级菜单
            if (menu.getLevel().intValue()==1){
                map1.put(menu.getId(),menu);
                menuDTOList1.add(menu);
            }

        }
        for (MenuDTO menu:menuDTOList){                        //找出所有的二级菜单
            if (menu.getLevel().intValue()==2)
                map1.get(menu.getParentMenuId()).addChild(menu);
        }

        MenuDTO rootMenu=null;
        if (menuDTOList1.size()>0){
            rootMenu=toDTO(menuDTOList1.get(0).getMenuByParentMenuId(),MenuDTO.class);
            rootMenu.setChildren(menuDTOList1);
        }

        return rootMenu;
    }



    public void save(MenuDTO dto){
        Menu model = new Menu();
        toModel(model, dto);
        menuDAO.create(model);
    }


    public MenuDTO load(Long id) {
        Menu model = menuDAO.load(id);
        return toDTO(model,MenuDTO.class);
    }

    /**
     * 这个方法会更新所有发生了变化的字段
     * @param dto
     */
    public void updateAllField(MenuDTO dto) {
        Menu model = menuDAO.load(dto.getId());
        toModel(model, dto);


        menuDAO.update(model);
    }

    public void deleteById(Long id) {
        menuDAO.deleteById(id);
    }

    public void deleteByIds(Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                Menu menu = menuDAO.load(id);
                menu.setDeleted(true);
                menuDAO.update(menu);
            }
        }
    }

    public List<MenuDTO> loadByLevel(MenuLevels level) {
        MenuDTO dto = new MenuDTO();
        dto.setLevel(level.level());
        List<Menu> models = menuDAO.find(dto, null);
        return toDTOs(models,MenuDTO.class);
    }

}
