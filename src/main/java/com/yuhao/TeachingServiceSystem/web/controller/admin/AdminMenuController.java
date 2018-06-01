package com.yuhao.TeachingServiceSystem.web.controller.admin;


import com.yuhao.TeachingServiceSystem.common.LogModule;
import com.yuhao.TeachingServiceSystem.common.MenuLevels;
import com.yuhao.TeachingServiceSystem.common.Module;
import com.yuhao.TeachingServiceSystem.dto.MenuDTO;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/admin/menu", produces="text/html;charset=UTF-8")
@Module(name = LogModule.Menu)
public class AdminMenuController extends BaseController{



    @RequestMapping(value = "/list")
    public String list(MenuDTO dto, Map<String, Object> map, Page page) {
        List<MenuDTO> dtos = menuService.findAllMenu(dto);
        map.put("list", dtos);
        map.put("query", dto);
        return "admin/menu/menu_list";
    }

    @RequestMapping(value = "/detail")
    public String detail(Map<String, Object> map, Long id) {
        if (id != null) {
            MenuDTO dto = menuService.load(id);
            map.put("n", dto);
            if (dto.getLevel() == MenuLevels.Level2.level()) {
                // load出所有的一级菜单
                map.put("parentLevelMenus", menuService.loadByLevel(MenuLevels.Level1));
            } else if (dto.getLevel() == MenuLevels.Level1.level()) {
                map.put("parentLevelMenus", menuService.loadByLevel(MenuLevels.Level0));
            }
        }
        map.put("parents", menuService.loadByLevel(MenuLevels.Level1));
        return "admin/menu/menu_detail";
    }


    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(MenuDTO dto) throws IOException {

        if (dto.getLevel() == 1) {
            dto.setParentMenuId(menuService.loadByLevel(MenuLevels.Level0).get(0).getId());
        }
        if (dto.getId() == null) {
            menuService.save(dto);
        } else {
            MenuDTO old = menuService.load(dto.getId());
            old.setName(dto.getName());
            old.setHref(dto.getHref());
            old.setParentMenuId(dto.getParentMenuId());
            old.setLevel(dto.getLevel());
            old.setSeq(dto.getSeq());
            old.setShow(dto.getShow());
            old.setIcon(dto.getIcon());
            menuService.updateAllField(old);
        }
        return ok();
    }




}


