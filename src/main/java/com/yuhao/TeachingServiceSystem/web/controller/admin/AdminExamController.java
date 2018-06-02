package com.yuhao.TeachingServiceSystem.web.controller.admin;


import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.yuhao.TeachingServiceSystem.dto.CourseDTO;
import com.yuhao.TeachingServiceSystem.service.CourseService;
import com.yuhao.TeachingServiceSystem.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.dto.ExamDTO;
import com.yuhao.TeachingServiceSystem.service.ExamService;
import com.yuhao.TeachingServiceSystem.web.controller.BaseController;


@Controller
@RequestMapping(value = "/admin/exam", produces="text/html;charset=UTF-8")
public class AdminExamController extends BaseController {

    @Autowired
    private ExamService examService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentCourseService studentCourseService;

    @RequestMapping(value = "/detail")
    public String detail(Map<String, Object> map, Long id) {
        if (id != null) {
            ExamDTO dto = examService.load(id);
            map.put("n", dto);
        }
        List<CourseDTO> courseDTOList=courseService.findAll();

        map.put("courseDTOList",courseDTOList);

        return "admin/exam/exam_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(ExamDTO dto) throws Exception {
        if (dto.getId() == null) {
            examService.create(dto);
        } else {

            ExamDTO old = examService.load(dto.getId());
            old.setCourseNumber(dto.getCourseNumber());
            old.setExamTime(dto.getExamTime());
            old.setExamPlace(dto.getExamPlace());
            old.setExamCapacity(dto.getExamCapacity());
            old.setInvigilator(dto.getInvigilator());
            old.setRemark(dto.getRemark());
            examService.updateAllFields(old);

        }
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Long[] ids) throws IOException {
        examService.deleteByIds(ids);
        return ok();
    }

    @RequestMapping(value = "/list")
    public String list(ExamDTO dto, Boolean search, Map<String, Object> map, Page page) {
        List<ExamDTO> dtos = examService.find(dto, page);
        map.put("list", dtos);
        map.put("query", dto);
        map.put("search", search);
        return "admin/exam/exam_list";
    }

    @RequestMapping(value = "/student")
    public String student(CourseDTO dto, Boolean search, Map<String, Object> map, Page page){
        List<CourseDTO> list=studentCourseService.findIn(getCurrentUserDTO());

        List<ExamDTO> examDTOList=examService.find(list);

        map.put("list", examDTOList);
        map.put("query", dto);
        map.put("search", search);

        return "admin/exam/exam_stu";

    }


}  
