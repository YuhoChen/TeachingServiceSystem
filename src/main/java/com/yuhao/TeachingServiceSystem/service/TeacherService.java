package com.yuhao.TeachingServiceSystem.service;


import java.util.ArrayList;
import java.util.List;

import com.yuhao.TeachingServiceSystem.common.UserType;
import com.yuhao.TeachingServiceSystem.dao.RoleDAO;
import com.yuhao.TeachingServiceSystem.dao.UserDAO;
import com.yuhao.TeachingServiceSystem.dao.UserRoleDAO;
import com.yuhao.TeachingServiceSystem.model.Role;
import com.yuhao.TeachingServiceSystem.model.User;
import com.yuhao.TeachingServiceSystem.model.UserRole;
import com.yuhao.TeachingServiceSystem.security.PasswordEncoder;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.model.Teacher;
import com.yuhao.TeachingServiceSystem.dto.TeacherDTO;
import com.yuhao.TeachingServiceSystem.dao.TeacherDAO;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TeacherService extends BaseService{

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserRoleDAO userRoleDAO;


    public List<TeacherDTO> find(TeacherDTO query,Page page){

        List<Teacher> models = teacherDAO.find(query, page);
        List<TeacherDTO> dtos = toDTOs(models,TeacherDTO.class);
        return dtos;

    }
     public List<TeacherDTO> findAll() {
         return this.find(new TeacherDTO(), null);
     }

     public List<TeacherDTO> findAll(TeacherDTO query) {
          return this.find(query, null);
     }

     public List<TeacherDTO> find(Page page) {
           return this.find(new TeacherDTO(), page);
     }

     public List<TeacherDTO> find(int count) {
            Page page = new Page();
            page.setPage(1);
            page.setPageSize(count);
            return this.find(new TeacherDTO(), page);
     }

     @Transactional
     public Long create(TeacherDTO dto){

         Teacher model = new Teacher();
         dto.setPassword(passwordEncoder.encodePassword(dto.getPassword(),dto.getTeacherNumber()));
         toModel(model, dto);
         Long id=teacherDAO.create(model);
         model=teacherDAO.load(id);

         User newUser=new User();
         newUser.setUsername(model.getTeacherNumber());
         newUser.setPassword(model.getPassword());
         newUser.setNickname(model.getName());
         newUser.setType(UserType.TEACHER.getValue());
         newUser.setHeadImage("http://7xjd68.com1.z0.glb.clouddn.com/a98a346d-a782-4f35-964a-3df11f4171cb");

         Long userId=userDAO.create(newUser);

         UserRole userRole=new UserRole();

         userRole.setRoleId(new Long(6));
         userRole.setUserId(userId);

         userRoleDAO.create(userRole);

         return id;
     }

     public TeacherDTO load(Long id) {
             Teacher model = teacherDAO.load(id);
              return toDTO(model,TeacherDTO.class);
     }

      public void updateAllFields(TeacherDTO dto) {
              Teacher model = teacherDAO.load(dto.getId());
              toModel(model, dto);
               teacherDAO.update(model);
      }

      public void deleteByIds(Long[] ids) {
            throw new RuntimeException("默认禁止删除，请确认是物理删除还是逻辑删除");
             /*
              if (ids != null) {
               for (Long id : ids) {
                    teacherDAO.deleteById(id);
                }
               } */
      }

      public void deleteById(Long id) {
           this.deleteByIds(new Long[] {id});
      }

      public TeacherDTO findOne(TeacherDTO query) {
            Teacher model = teacherDAO.findOne(query);
            return toDTO(model,TeacherDTO.class);
      }

}  
