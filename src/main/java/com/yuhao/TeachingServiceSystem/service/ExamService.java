package com.yuhao.TeachingServiceSystem.service;


import java.util.ArrayList;
import java.util.List;

import com.yuhao.TeachingServiceSystem.dao.CourseDAO;
import com.yuhao.TeachingServiceSystem.dto.CourseDTO;
import com.yuhao.TeachingServiceSystem.model.Course;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.model.Exam;
import com.yuhao.TeachingServiceSystem.dto.ExamDTO;
import com.yuhao.TeachingServiceSystem.dao.ExamDAO;


@Service
public class ExamService extends BaseService{

    @Autowired
    private ExamDAO examDAO;

    @Autowired
    private CourseDAO courseDAO;

    public List<ExamDTO> find(ExamDTO query,Page page){

        List<Exam> models = examDAO.find(query, page);
        List<ExamDTO> dtos = toDTOs(models,ExamDTO.class);
        return dtos;

    }
     public List<ExamDTO> findAll() {
         return this.find(new ExamDTO(), null);
     }

     public List<ExamDTO> findAll(ExamDTO query) {
          return this.find(query, null);
     }

     public List<ExamDTO> find(Page page) {
           return this.find(new ExamDTO(), page);
     }

     public List<ExamDTO> find(int count) {
            Page page = new Page();
            page.setPage(1);
            page.setPageSize(count);
            return this.find(new ExamDTO(), page);
     }

     public Long create(ExamDTO dto) {
             Exam model = new Exam();
             toModel(model, dto);
             Course course=courseDAO.get(dto.getCourseNumber().getId());
             model.setCourseNumber(course);

             return examDAO.create(model);
     }

     public ExamDTO load(Long id) {
             Exam model = examDAO.load(id);
              return toDTO(model,ExamDTO.class);
     }

      public void updateAllFields(ExamDTO dto) {
              Exam model = examDAO.load(dto.getId());
              toModel(model, dto);
               examDAO.update(model);
      }

      public void deleteByIds(Long[] ids) {

              if (ids != null) {
               for (Long id : ids) {
                    examDAO.deleteById(id);
                }
               }
      }

      public void deleteById(Long id) {
           this.deleteByIds(new Long[] {id});
      }

      public ExamDTO findOne(ExamDTO query) {
            Exam model = examDAO.findOne(query);
            return toDTO(model,ExamDTO.class);
      }

      public List<ExamDTO> find(List<CourseDTO> list){

        List<ExamDTO> examDTOList=new ArrayList<ExamDTO>();

          for (CourseDTO c:list) {

              ExamDTO examDTO=new ExamDTO();
              Course course=new Course();
              course.setId(c.getId());
              examDTO.setCourseNumber(course);

              Exam exam=examDAO.findOne(examDTO);
              if (exam!=null){
                  examDTOList.add(toDTO(exam,ExamDTO.class));
              }

          }

          return examDTOList;
      }



}  
