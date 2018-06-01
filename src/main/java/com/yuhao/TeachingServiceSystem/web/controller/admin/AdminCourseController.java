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
import com.yuhao.TeachingServiceSystem.dto.CourseDTO;
import com.yuhao.TeachingServiceSystem.service.CourseService;
import com.yuhao.TeachingServiceSystem.web.controller.BaseController;


@Controller
@RequestMapping(value = "/admin/course", produces="text/html;charset=UTF-8")
public class AdminCourseController extends BaseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/detail")
    public String detail(Map<String, Object> map, Long id) {
        if (id != null) {
            CourseDTO dto = courseService.load(id);
            map.put("n", dto);
        }
        return "admin/course/course_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(CourseDTO dto) throws Exception {
        if (dto.getId() == null) {
            courseService.create(dto);
        } else {
            CourseDTO old = courseService.load(dto.getId());
//            old.setCourseNumber(dto.getCourseNumber());
            old.setCourseName(dto.getCourseName());
            old.setTeacher(dto.getTeacher());
            old.setCourseInfo(dto.getCourseInfo());
            old.setCourseCredit(dto.getCourseCredit());
            courseService.updateAllFields(old);

        }
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Long[] ids) throws IOException {
        courseService.deleteByIds(ids);
        return ok();
    }

    @RequestMapping(value = "/list")
    public String list(CourseDTO dto, Boolean search, Map<String, Object> map, Page page) {
        List<CourseDTO> dtos = courseService.find(dto, page);
        map.put("list", dtos);
        map.put("query", dto);
        map.put("search", search);
        map.put("page",page);
        return "admin/course/course_list";
    }

}  
