package com.yuhao.TeachingServiceSystem.service;


import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.model.Course;
import com.yuhao.TeachingServiceSystem.dto.CourseDTO;
import com.yuhao.TeachingServiceSystem.dao.CourseDAO;


@Service
public class CourseService extends BaseService{

    @Autowired
    private CourseDAO courseDAO;

    public List<CourseDTO> find(CourseDTO query,Page page){

        List<Course> models = courseDAO.find(query, page);
        List<CourseDTO> dtos = toDTOs(models,CourseDTO.class);
        return dtos;

    }
     public List<CourseDTO> findAll() {
         return this.find(new CourseDTO(), null);
     }

     public List<CourseDTO> findAll(CourseDTO query) {
          return this.find(query, null);
     }

     public List<CourseDTO> find(Page page) {
           return this.find(new CourseDTO(), page);
     }

     public List<CourseDTO> find(int count) {
            Page page = new Page();
            page.setPage(1);
            page.setPageSize(count);
            return this.find(new CourseDTO(), page);
     }

     public Long create(CourseDTO dto) {
             Course model = new Course();
             toModel(model, dto);
             return courseDAO.create(model);
     }

     public CourseDTO load(Long id) {
             Course model = courseDAO.load(id);
              return toDTO(model,CourseDTO.class);
     }

      public void updateAllFields(CourseDTO dto) {
              Course model = courseDAO.load(dto.getId());
              toModel(model, dto);
               courseDAO.update(model);
      }

      public void deleteByIds(Long[] ids) {
            throw new RuntimeException("默认禁止删除，请确认是物理删除还是逻辑删除");
             /*
              if (ids != null) {
               for (Long id : ids) {
                    courseDAO.deleteById(id);
                }
               } */
      }

      public void deleteById(Long id) {
           this.deleteByIds(new Long[] {id});
      }

      public CourseDTO findOne(CourseDTO query) {
            Course model = courseDAO.findOne(query);
            return toDTO(model,CourseDTO.class);
      }

}  
