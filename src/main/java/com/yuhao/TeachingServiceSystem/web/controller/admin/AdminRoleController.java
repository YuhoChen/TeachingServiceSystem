package com.yuhao.TeachingServiceSystem.web.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.yuhao.TeachingServiceSystem.common.LogModule;
import com.yuhao.TeachingServiceSystem.common.MenuLevels;
import com.yuhao.TeachingServiceSystem.common.Module;
import com.yuhao.TeachingServiceSystem.dto.*;
import com.yuhao.TeachingServiceSystem.model.Authority;
import com.yuhao.TeachingServiceSystem.model.Menu;
import com.yuhao.TeachingServiceSystem.model.User;
import com.yuhao.TeachingServiceSystem.service.RoleAuthorityService;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.util.SpringSecurityUtils;
import com.yuhao.TeachingServiceSystem.web.controller.BaseController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


@Module(name = LogModule.Role)
@Controller
@RequestMapping(value = "/admin/role", produces="text/html;charset=UTF-8")
public class AdminRoleController extends BaseController {


    @RequestMapping(value = "/detail")
    public String detail(Map<String, Object> map, Long id) {

        if (id != null) {
            RoleDTO n = roleService.load(id);
            map.put("n", n);
        }
        return "admin/role/role_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(HttpServletResponse response, RoleDTO dto) throws IOException {
        if (dto.getId() == null) {
            dto.setEditAble(true);
            roleService.save(dto);
        } else {
            roleService.update(dto);
        }
        return ok();
    }




    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete( Long[] ids) throws IOException {
        roleService.deleteByIds(ids);

        return ok();
    }

    @RequestMapping(value = "/list")
    public String list(RoleDTO dto, Map<String, Object> map, Page page) {
        map.put("list", roleService.list(dto, page));
        return "admin/role/role_list";
    }

    @ResponseBody
    @RequestMapping(value = "/find")
    public void find() {

    }


    @RequestMapping("authorityDetail")
    public String authorityDetail(Long roleId, Map<String, Object> map) throws Exception {


        List<ModuleAuthorityDTO> moduleAuthorityDTOs = authorityService.findModuleAuthories();
        List<AuthorityDTO> authoritiesDb = roleAuthorityService.findByAuthoritiesByRoleId(roleId);
        for (ModuleAuthorityDTO moduleAuthorityDTO : moduleAuthorityDTOs) {
            for (AuthorityDTO a : moduleAuthorityDTO.getAuthorities()) {
                for (AuthorityDTO dbAuthority : authoritiesDb) {
                    if (StringUtils.isNotBlank(dbAuthority.getCode()) && dbAuthority.getCode().equals(a.getCode())) {
                        a.setContains(true);
                    }
                }
            }
        }

        map.put("moduleAuthorities", moduleAuthorityDTOs);
        map.put("roleId", roleId);


        return "admin/role/role_authorityDetail";

    }

    @ResponseBody
    @RequestMapping("getAuthorities")
    public String getAuthorities(Long roleId) throws Exception {


        return ok();
    }

    @ResponseBody
    @RequestMapping("updateAuthority")
    public String updateAuthority(Long roleId, Long[] authorityIds) throws Exception {
        roleAuthorityService.update(roleId, authorityIds);
        return ok();
    }


    @RequestMapping("roleMenu")
    public String roleMenu(Long roleId) throws Exception {

        return "admin/role/role_roleMenu";
    }

    @ResponseBody
    @RequestMapping("getMenus")
    public String getMenus(Long roleId) throws Exception {
        List<MenuDTO> roleMenuDTOs = roleMenuService.findMenuByRoleId(roleId);

        menuService.getRootMenu(roleMenuDTOs); //组织菜单间的关系

        List<MenuDTO> allMenus=menuService.findAllMenu(new MenuDTO());

        MenuDTO rootMneu=menuService.getRootMenu(allMenus);

        JSONArray result = new JSONArray();
        for (MenuDTO l1 : rootMneu.getChildren()) {
            JSONObject j1 = new JSONObject();
            j1.put("text", l1.getName());
            j1.put("id", l1.getId());
            JSONArray array = new JSONArray();
            if (l1.getChildren()!=null) {
                for (MenuDTO l2 : l1.getChildren()) {
                    JSONObject j2 = new JSONObject();
                    j2.put("text", l2.getName());
                    j2.put("id", l2.getId());
                    JSONObject state = new JSONObject();
                    state.put("disabled", false);
                    state.put("opened", false);
                    boolean contains = ifContains(roleMenuDTOs, l2.getId());
                    state.put("selected", contains);
                    j2.put("state", state);
                    array.add(j2);
                }
            }
            j1.put("children", array);
            result.add(j1);
        }

        return result.toJSONString();


    }


    private boolean ifContains(List<MenuDTO> roleMenuDTOs, Long id) {
        for (MenuDTO rm : roleMenuDTOs) {
            if (rm.getId()== id) {
                return true;
            }
            if(rm.getChildren()!=null)
                for (MenuDTO m : rm.getChildren()) {
                    if (m.getId() == id) {
                        return true;
                    }
                }
        }
        return false;
    }




    @ResponseBody
    @RequestMapping("updateRoleMenu")
    public String updateRoleMenu(Long roleId, Long[] menuIds) throws Exception {
        roleMenuService.update(roleId, menuIds);
        return ok();
    }
}
