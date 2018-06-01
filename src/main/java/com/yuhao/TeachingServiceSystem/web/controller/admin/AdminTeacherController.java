package com.yuhao.TeachingServiceSystem.web.controller.admin;


import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.dto.TeacherDTO;
import com.yuhao.TeachingServiceSystem.service.TeacherService;
import com.yuhao.TeachingServiceSystem.web.controller.BaseController;


@Controller
@RequestMapping(value = "/admin/teacher", produces="text/html;charset=UTF-8")
public class AdminTeacherController extends BaseController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/detail")
    public String detail(Map<String, Object> map, Long id) {
        if (id != null) {
            TeacherDTO dto = teacherService.load(id);
            map.put("n", dto);
        }
        return "admin/teacher/teacher_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(TeacherDTO dto) throws Exception {
        if (dto.getId() == null) {
            teacherService.create(dto);
        } else {

            TeacherDTO old = teacherService.load(dto.getId());
//            old.setTeacherNumber(dto.getTeacherNumber());
//            old.setName(dto.getName());
//            old.setSex(dto.getSex());
//            old.setIdCard(dto.getIdCard());
            old.setBirth(dto.getBirth());
            old.setNativePlace(dto.getNativePlace());
            old.setEmail(dto.getEmail());
            old.setPhone(dto.getPhone());
//            old.setCollege(dto.getCollege());
            teacherService.updateAllFields(old);

        }
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Long[] ids) throws IOException {
        teacherService.deleteByIds(ids);
        return ok();
    }

    @RequestMapping(value = "/list")
    public String list(TeacherDTO dto, Boolean search, Map<String, Object> map, Page page) {
        List<TeacherDTO> dtos = teacherService.find(dto, page);
        map.put("list", dtos);
        map.put("query", dto);
        map.put("search", search);
        map.put("page",page);
        return "admin/teacher/teacher_list";
    }

}  
