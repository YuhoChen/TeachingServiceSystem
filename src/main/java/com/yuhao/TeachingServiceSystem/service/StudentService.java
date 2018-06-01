package com.yuhao.TeachingServiceSystem.service;


import java.util.ArrayList;
import java.util.List;

import com.yuhao.TeachingServiceSystem.common.UserType;
import com.yuhao.TeachingServiceSystem.dao.UserDAO;
import com.yuhao.TeachingServiceSystem.dao.UserRoleDAO;
import com.yuhao.TeachingServiceSystem.model.Teacher;
import com.yuhao.TeachingServiceSystem.model.User;
import com.yuhao.TeachingServiceSystem.model.UserRole;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.model.Student;
import com.yuhao.TeachingServiceSystem.dto.StudentDTO;
import com.yuhao.TeachingServiceSystem.dao.StudentDAO;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StudentService extends BaseService{

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;


    public List<StudentDTO> find(StudentDTO query,Page page){

        List<Student> models = studentDAO.find(query, page);
        List<StudentDTO> dtos = toDTOs(models,StudentDTO.class);
        return dtos;

    }
     public List<StudentDTO> findAll() {
         return this.find(new StudentDTO(), null);
     }

     public List<StudentDTO> findAll(StudentDTO query) {
          return this.find(query, null);
     }

     public List<StudentDTO> find(Page page) {
           return this.find(new StudentDTO(), page);
     }

     public List<StudentDTO> find(int count) {
            Page page = new Page();
            page.setPage(1);
            page.setPageSize(count);
            return this.find(new StudentDTO(), page);
     }

     @Transactional
     public Long create(StudentDTO dto) {

         Student model = new Student();
         dto.setPassword(passwordEncoder.encodePassword(dto.getPassword(),dto.getStudentNumber()));
         toModel(model, dto);
         Long id=studentDAO.create(model);
         model=studentDAO.load(id);

         User newUser=new User();
         newUser.setUsername(model.getStudentNumber());
         newUser.setPassword(model.getPassword());
         newUser.setNickname(model.getName());
         newUser.setType(UserType.STUDENT.getValue());
         newUser.setHeadImage("http://7xjd68.com1.z0.glb.clouddn.com/a98a346d-a782-4f35-964a-3df11f4171cb");

         Long userId=userDAO.create(newUser);

         UserRole userRole=new UserRole();

         userRole.setRoleId(new Long(7));
         userRole.setUserId(userId);

         userRoleDAO.create(userRole);

         return id;
     }

     public StudentDTO load(Long id) {
             Student model = studentDAO.load(id);
              return toDTO(model,StudentDTO.class);
     }

      public void updateAllFields(StudentDTO dto) {
              Student model = studentDAO.load(dto.getId());
              toModel(model, dto);
               studentDAO.update(model);
      }

      public void deleteByIds(Long[] ids) {
            throw new RuntimeException("默认禁止删除，请确认是物理删除还是逻辑删除");
             /*
              if (ids != null) {
               for (Long id : ids) {
                    studentDAO.deleteById(id);
                }
               } */
      }

      public void deleteById(Long id) {
           this.deleteByIds(new Long[] {id});
      }

      public StudentDTO findOne(StudentDTO query) {
            Student model = studentDAO.findOne(query);
            return toDTO(model,StudentDTO.class);
      }

}  
