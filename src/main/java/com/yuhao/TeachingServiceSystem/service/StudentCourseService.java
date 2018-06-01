package com.yuhao.TeachingServiceSystem.service;

import java.util.ArrayList;
import java.util.List;

import com.yuhao.TeachingServiceSystem.dao.CourseDAO;
import com.yuhao.TeachingServiceSystem.dao.StudentDAO;
import com.yuhao.TeachingServiceSystem.dto.CourseDTO;
import com.yuhao.TeachingServiceSystem.dto.StudentDTO;
import com.yuhao.TeachingServiceSystem.dto.UserDTO;
import com.yuhao.TeachingServiceSystem.exception.BizException;
import com.yuhao.TeachingServiceSystem.model.Course;
import com.yuhao.TeachingServiceSystem.model.Student;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.model.StudentCourse;
import com.yuhao.TeachingServiceSystem.dto.StudentCourseDTO;
import com.yuhao.TeachingServiceSystem.dao.StudentCourseDAO;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentCourseService extends BaseService{

    @Autowired
    private StudentCourseDAO studentCourseDAO;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private CourseDAO courseDAO;


    public List<StudentCourseDTO> find(StudentCourseDTO query,Page page){

        List<StudentCourse> models = studentCourseDAO.find(query, page);
        List<StudentCourseDTO> dtos = toDTOs(models,StudentCourseDTO.class);
        return dtos;

    }
     public List<StudentCourseDTO> findAll() {
         return this.find(new StudentCourseDTO(), null);
     }

     public List<StudentCourseDTO> findAll(StudentCourseDTO query) {
          return this.find(query, null);
     }

     public List<StudentCourseDTO> find(Page page) {
           return this.find(new StudentCourseDTO(), page);
     }

     public List<StudentCourseDTO> find(int count) {
            Page page = new Page();
            page.setPage(1);
            page.setPageSize(count);
            return this.find(new StudentCourseDTO(), page);
     }

     public List<CourseDTO> findIn(UserDTO userDTO){

         StudentDTO studentDTO=new StudentDTO();
         studentDTO.setStudentNumber(userDTO.getUsername());

         Student student=studentDAO.findOne(studentDTO);

         StudentCourseDTO studentCourseDTO=new StudentCourseDTO();
         studentCourseDTO.setStudentNumber(student);

         List<StudentCourse> studentCourseList=studentCourseDAO.find(studentCourseDTO,null);

         List<CourseDTO> list=new ArrayList<CourseDTO>();

         for (StudentCourse studentCourse:studentCourseList) {
             list.add(toDTO(studentCourse.getCourseNumber(),CourseDTO.class));
         }

        return list;

     }




     public Long create(StudentCourseDTO dto) {
             StudentCourse model = new StudentCourse();
             toModel(model, dto);
             return studentCourseDAO.create(model);
     }

     public StudentCourseDTO load(Long id) {
             StudentCourse model = studentCourseDAO.load(id);
              return toDTO(model,StudentCourseDTO.class);
     }

      public void updateAllFields(StudentCourseDTO dto) {
              StudentCourse model = studentCourseDAO.load(dto.getId());
              toModel(model, dto);
               studentCourseDAO.update(model);
      }

      public void deleteByIds(Long[] ids) {

              if (ids != null) {
               for (Long id : ids) {
                    studentCourseDAO.deleteById(id);
                }
               }
      }

      public void deleteById(Long id) {
           this.deleteByIds(new Long[] {id});
      }

      public StudentCourseDTO findOne(StudentCourseDTO query) {
            StudentCourse model = studentCourseDAO.findOne(query);
            return toDTO(model,StudentCourseDTO.class);
      }

      @Transactional(rollbackFor = Exception.class)
      public void updateElective(Long id, UserDTO userDTO)throws BizException{

          StudentDTO studentDTO=new StudentDTO();
          studentDTO.setStudentNumber(userDTO.getUsername());

          Student student=studentDAO.findOne(studentDTO);
          Course course=courseDAO.load(id);

          StudentCourse studentCourse=new StudentCourse();
          studentCourse.setCourseNumber(course);
          studentCourse.setStudentNumber(student);

          studentCourseDAO.create(studentCourse);

      }


      public void deleteElective(UserDTO userDTO,Long id){

          StudentDTO studentDTO=new StudentDTO();
          studentDTO.setStudentNumber(userDTO.getUsername());

          Student student=studentDAO.findOne(studentDTO);
          Course course=courseDAO.get(id);

          StudentCourseDTO studentCourse=new StudentCourseDTO();
          studentCourse.setCourseNumber(course);
          studentCourse.setStudentNumber(student);

          StudentCourse stc=studentCourseDAO.findOne(studentCourse);

          if (stc!=null){
              deleteById(stc.getId());
          }

      }



}  
