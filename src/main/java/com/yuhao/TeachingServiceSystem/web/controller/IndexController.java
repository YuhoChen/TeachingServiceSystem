package com.yuhao.TeachingServiceSystem.web.controller;

import com.yuhao.TeachingServiceSystem.common.UserType;
import com.yuhao.TeachingServiceSystem.dao.MenuDAO;
import com.yuhao.TeachingServiceSystem.dto.*;
import com.yuhao.TeachingServiceSystem.model.Menu;
import com.yuhao.TeachingServiceSystem.model.Role;
import com.yuhao.TeachingServiceSystem.service.MenuService;
import com.yuhao.TeachingServiceSystem.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class IndexController extends BaseController{

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/index")
    public String index(Map<String, Object> map) {
        return "redirect:/admin/index";
    }


    @RequestMapping(value = "/admin/index")
    public String adminIndex(Map<String, Object> map) {


        UserDTO currentUser=this.getCurrentUserDTO();

        if (currentUser==null)
            return "redirect:/admin/login";

        List<RoleDTO> roleDTOList=userRoleService.findRolesByUserId(currentUser.getId());

        List<MenuDTO> menuDTOList=new ArrayList<MenuDTO>();

        for (RoleDTO roleDTO:roleDTOList){
            menuDTOList.addAll(roleMenuService.findMenuByRoleId(roleDTO.getId()));
        }


        MenuDTO rootMenu=menuService.getRootMenu(menuDTOList);

        map.put("rootMenu", rootMenu);
        map.put("user", currentUser);


        return "admin/index";

    }

    @RequestMapping(value = "/admin/login")
    public String adminLogin(Map<String, Object> map) {

        List<NoticeDTO> list=noticeService.findAll();
        map.put("list",list);
        return "admin/login_v2";

    }

    @RequestMapping(value = "/notice")
    public String detail(Long id,Map<String, Object> map){

        map.put("notice",noticeService.load(id));

        return "admin/notice_detail";


    }



    @RequestMapping("/admin/index/index_v3")
    public String index_v3(Map<String, Object> map) throws Exception {
        return "admin/index_v3";
    }


}
