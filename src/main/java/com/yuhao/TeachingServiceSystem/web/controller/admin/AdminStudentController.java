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
import com.yuhao.TeachingServiceSystem.dto.StudentDTO;
import com.yuhao.TeachingServiceSystem.service.StudentService;
import com.yuhao.TeachingServiceSystem.web.controller.BaseController;


@Controller
@RequestMapping(value = "/admin/student", produces="text/html;charset=UTF-8")
public class AdminStudentController extends BaseController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/detail")
    public String detail(Map<String, Object> map, Long id) {
        if (id != null) {
            StudentDTO dto = studentService.load(id);
            map.put("n", dto);
        }
        return "admin/student/student_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(StudentDTO dto) throws Exception {
        if (dto.getId() == null) {
            studentService.create(dto);
        } else {

            StudentDTO old = studentService.load(dto.getId());

//            old.setStudentNumber(dto.getStudentNumber());
//            old.setName(dto.getName());
//            old.setSex(dto.getSex());
//            old.setIdCard(dto.getIdCard());
            old.setBirth(dto.getBirth());
            old.setNativePlace(dto.getNativePlace());
            old.setEmail(dto.getEmail());
            old.setPhone(dto.getPhone());
//            old.setCollege(dto.getCollege());
//            old.setClass(dto.getClass());
//            old.setPassword(dto.getPassword());
            studentService.updateAllFields(old);

        }
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Long[] ids) throws IOException {
        studentService.deleteByIds(ids);
        return ok();
    }

    @RequestMapping(value = "/list")
    public String list(StudentDTO dto, Boolean search, Map<String, Object> map, Page page) {
        List<StudentDTO> dtos = studentService.find(dto, page);
        map.put("list", dtos);
        map.put("query", dto);
        map.put("search", search);
        map.put("page",page);
        return "admin/student/student_list";
    }

}  
