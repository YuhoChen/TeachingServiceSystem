package com.yuhao.TeachingServiceSystem.web.controller.admin;


import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.yuhao.TeachingServiceSystem.common.UserType;
import com.yuhao.TeachingServiceSystem.dto.CourseDTO;
import com.yuhao.TeachingServiceSystem.dto.StudentDTO;
import com.yuhao.TeachingServiceSystem.dto.UserDTO;
import com.yuhao.TeachingServiceSystem.exception.BizException;
import com.yuhao.TeachingServiceSystem.service.CourseService;
import com.yuhao.TeachingServiceSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.dto.StudentCourseDTO;
import com.yuhao.TeachingServiceSystem.service.StudentCourseService;
import com.yuhao.TeachingServiceSystem.web.controller.BaseController;


@Controller
@RequestMapping(value = "/admin/studentCourse", produces="text/html;charset=UTF-8")
public class AdminStudentCourseController extends BaseController {

    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private CourseService courseService;




    @RequestMapping(value = "/detail")
    public String detail(Map<String, Object> map, Long id) {
        if (id != null) {
            StudentCourseDTO dto = studentCourseService.load(id);
            map.put("n", dto);
        }
        return "admin/studentCourse/studentCourse_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(StudentCourseDTO dto) throws Exception {
        if (dto.getId() == null) {
            studentCourseService.create(dto);
        } else {
            throw new RuntimeException("请实现更新逻辑，并删除该行");
            /*
            StudentCourseDTO old = studentCourseService.load(dto.getId());
            // 确定哪些字段是在详情页面需要修改的，不在详情页修改的，就不需要在这里set值。updated和updatedBy不需要在这里处理，框架会统一处理
            old.setId(dto.getId());
            old.setStudentNumber(dto.getStudentNumber());
            old.setCourseNumber(dto.getCourseNumber());
            old.setScore(dto.getScore());
            studentCourseService.updateAllFields(old);
            */
        }
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Long[] ids) throws IOException {
        studentCourseService.deleteByIds(ids);
        return ok();
    }

    @RequestMapping(value = "/list")
    public String list(CourseDTO dto, Boolean search, Map<String, Object> map, Page page) {
        List<CourseDTO> dtos = courseService.find(dto, page);
        List<CourseDTO> list=studentCourseService.findIn(getCurrentUserDTO());
        for (CourseDTO c:dtos) {
            for (CourseDTO c1:list) {
                if (c.getCourseNumber().equals(c1.getCourseNumber())){
                    c.setSelected(true);
                    break;
                }
            }
        }
        map.put("list", dtos);
        map.put("query", dto);
        map.put("search", search);
        map.put("page",page);
        return "admin/studentCourse/studentCourse_list";
    }
    @RequestMapping(value = "/result")
    public String result(CourseDTO dto, Boolean search, Map<String, Object> map, Page page){

        List<CourseDTO> list=studentCourseService.findIn(getCurrentUserDTO());
        map.put("list", list);
        map.put("query", dto);
        map.put("search", search);
        map.put("page",page);
        return "admin/studentCourse/studentCourse_result";
    }



    @ResponseBody
    @RequestMapping(value = "/elective")
    public String elective(Long id){

        UserDTO userDTO=getCurrentUserDTO();

        if (userDTO==null||userDTO.getType()!= UserType.STUDENT.getValue()){
            throw new BizException("不是学生，不能选课");
        }

        studentCourseService.updateElective(id,userDTO);


        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/retreat")
    public String retreat(Long id){

        studentCourseService.deleteElective(getCurrentUserDTO(),id);

        return ok();
    }




}  
