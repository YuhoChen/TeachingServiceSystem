package com.yuhao.TeachingServiceSystem.web.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yuhao.TeachingServiceSystem.common.UserType;
import com.yuhao.TeachingServiceSystem.dto.RoleDTO;
import com.yuhao.TeachingServiceSystem.dto.StudentDTO;
import com.yuhao.TeachingServiceSystem.dto.TeacherDTO;
import com.yuhao.TeachingServiceSystem.dto.UserDTO;
import com.yuhao.TeachingServiceSystem.exception.BizException;
import com.yuhao.TeachingServiceSystem.model.Role;
import com.yuhao.TeachingServiceSystem.service.*;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping(value = "/admin/user", produces="text/html;charset=UTF-8")
public class AdminUserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;


    @RequestMapping(value = "/detail")
    public String detail(Integer type, Long parentId, Map<String, Object> map, Long id, boolean seedUser) {
        if (id != null) {
            UserDTO dto = userService.load(id);
            map.put("n", dto);
        }

        map.put("type", type);
        map.put("parentId", parentId);
        return "/admin/user/user_detail";

    }

    @PreAuthorize("hasRole('user.add')")
    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(UserDTO dto) throws Exception {
        if (dto.getId() == null) {
            dto.setType(UserType.Normal.getValue());
            Long id = userService.create(dto);


        } else {
            UserDTO old = userService.load(dto.getId());
            userService.updateAllFields(old);
        }
        return ok();
    }


    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Long[] ids) throws IOException {
        userService.deleteByIds(ids);
        return ok();
    }

    @RequestMapping(value = "/list")
    public String list(Integer fromType, Integer type, UserDTO dto, Map<String, Object> map, Page page) {
        List<UserDTO> dtos = userService.find(dto, page);
        map.put("list", dtos);
        map.put("query", dto);
        map.put("type", type);
        map.put("fromType", fromType);
        return "admin/user/user_list";
    }

    @RequestMapping(value = "/edit")
    public String edit(Map<String, Object> map){

        UserDTO user=getCurrentUserDTO();
        if (user==null){
            throw new BizException("请重新登陆!");
        }

        if(user.getType()==UserType.STUDENT.getValue()){

            StudentDTO dto = new StudentDTO();
            dto.setStudentNumber(user.getUsername());
            dto=studentService.findOne(dto);
            map.put("n", dto);
            return "admin/student/student_detail";

        }else if(user.getType()==UserType.TEACHER.getValue()){

            TeacherDTO dto = new TeacherDTO();
            dto.setTeacherNumber(user.getUsername());
            dto=teacherService.findOne(dto);
            map.put("n", dto);
            return "admin/teacher/teacher_detail";
        }else {
            map.put("n",user);
            return "admin/user/user_detail";
        }

    }


    @RequestMapping(value = "/auth")
    public String auth(Map<String, Object> map, Long userId, RoleDTO dto, Page page) {
        if (userId != null) {
            List<RoleDTO> roles = userRoleService.findRolesByUserId(userId);
            List<RoleDTO> list = roleService.list(dto, page);
            for (RoleDTO model : list) {
                for (RoleDTO role : roles) {
                    if (role.getId() .equals( model.getId())) {
                        model.setChecked(true);
                    }
                }
            }
            map.put("userId", userId);
            map.put("list", list);
            map.put("query", dto);
        }
        return "admin/user/user_auth";
    }

    @RequestMapping(value = "/authSave")
    @ResponseBody
    public String authSave(Long[] ids, Long userId) {
        userRoleService.save(ids, userId);
        return ok();
    }

    @RequestMapping(value = "/reset")
    public String reset(Map<String, Object> map) {
        UserDTO dto = getCurrentUserDTO();
        map.put("n", dto);
        return "admin/user/user_reset";
    }

    @RequestMapping(value = "/editHeadImage")
    public String editHeadImage(Map<String, Object> map) {
        map.put("user", getCurrentUserDTO());
        return "admin/user/user_editHeadImage";
    }

    @RequestMapping("saveHeadImage")
    public String saveHeadImage(HttpServletRequest request) throws Exception {
        String file = saveFileAndReturnFileName(request, "file");
        UserDTO user = userService.load(getCurrentUserDTO().getId());
        if (StringUtils.isNotBlank(file)) {
            user.setHeadImage(file);
            userService.updateAllFields(user);
        }
        return successPage(null);
    }

    @ResponseBody
    @RequestMapping(value = "/resetPassword")
    public String resetPassword(String newPassword, Long userId) {
        userService.resetPassword(newPassword, userId);
        return ok();
    }

    @RequestMapping(value = "changePassword")
    public String changePassword(Map<String, Object> map) {
        UserDTO dto = getCurrentUserDTO();
        map.put("n", dto);
        return "admin/user/user_changePassword";
    }

    @ResponseBody
    @RequestMapping(value = "saveChangePassword")
    public String saveChangePassword(String oldPassword, String newPassword, String confirmPassword, Long userId, Map<String, Object> map) {
        userService.changePassword(userId, oldPassword, newPassword, confirmPassword);
        return ok();
    }

}

