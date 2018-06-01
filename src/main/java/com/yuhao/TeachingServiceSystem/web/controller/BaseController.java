package com.yuhao.TeachingServiceSystem.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.yuhao.TeachingServiceSystem.common.StatusCode;
import com.yuhao.TeachingServiceSystem.dto.UserDTO;
import com.yuhao.TeachingServiceSystem.security.PasswordEncoder;
import com.yuhao.TeachingServiceSystem.service.*;
import com.yuhao.TeachingServiceSystem.util.MyWebUtils;
import com.yuhao.TeachingServiceSystem.util.SpringSecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class BaseController {

    @Autowired
    protected UserService userService;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected RoleService roleService;

    @Autowired
    protected AuthorityService authorityService;

    @Autowired
    protected MenuService menuService;


    @Autowired
    protected RoleMenuService roleMenuService;

    @Autowired
    protected RoleAuthorityService roleAuthorityService;

    @Autowired
    protected UserRoleService userRoleService;

    @Autowired
    protected ImageService imageService;



    protected UserDTO getCurrentUserDTO() {
        UserDTO coreUser = SpringSecurityUtils.getCurrentUser();
        if (coreUser == null) {
            return null;
        }
        return userService.load(coreUser.getId());
    }



    protected String ok() {
        JSONObject json = new JSONObject();
        json.put("statusCode", StatusCode.OK.code());
        json.put("message", "操作成功");
        json.put("success", true);
        json.put("msg", "操作成功");
        return json.toJSONString();
    }

    protected String saveFileAndReturnFileName(HttpServletRequest request, String formInputName) throws IOException {
        MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
        MultipartFile mf = mhs.getFile(formInputName);
        String fileName = mf.getOriginalFilename();
        if (mf != null && !mf.isEmpty()) {
            return imageService.saveImage(fileName, mf.getBytes());
        }
        return fileName;
    }

    protected String successPage(String msg) {
        if (StringUtils.isBlank(msg)) {
            msg = "操作成功";
        }
        MyWebUtils.getCurrentRequest().setAttribute("msg", msg);
        return "common/success";
    }



}
