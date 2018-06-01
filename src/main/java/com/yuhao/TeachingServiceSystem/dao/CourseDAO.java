package com.yuhao.TeachingServiceSystem.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.model.Course;
import com.yuhao.TeachingServiceSystem.dto.CourseDTO;


@Repository
public class CourseDAO extends BaseDAO<Course>{


    public List<Course> find(CourseDTO dto,Page page){

            StringBuilder hql = new StringBuilder();
            List<Object> paras = new ArrayList<Object>();
            hql.append("From Course n where 1 = 1 ");

                    if(dto.getId()!=null){
                         hql.append(" and n.id = ? ");
                         paras.add(dto.getId());
                     }
                    if (StringUtils.isNotBlank(dto.getCourseNumber())) {
                        hql.append(" and n.courseNumber = ? ");
                        paras.add(dto.getCourseNumber());
                    }

                    if (StringUtils.isNotBlank(dto.getCourseName())) {
                        hql.append(" and n.courseName = ? ");
                        paras.add(dto.getCourseName());
                    }

                    if (StringUtils.isNotBlank(dto.getTeacher())) {
                        hql.append(" and n.teacher = ? ");
                        paras.add(dto.getTeacher());
                    }

                    if (StringUtils.isNotBlank(dto.getCourseInfo())) {
                        hql.append(" and n.courseInfo = ? ");
                        paras.add(dto.getCourseInfo());
                    }

                    if(dto.getCourseCredit()!=null){
                         hql.append(" and n.courseCredit = ? ");
                         paras.add(dto.getCourseCredit());
                     }

            hql.append(" order by n.id desc ");
            return super.find(hql.toString(), paras.toArray(), page);
    }

    public Course findOne(CourseDTO dto){
        Page page = new Page();
        page.setPageSize(1);
        page.setPage(1);

        List<Course> list = this.find(dto, page);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);

    }

    @Override
    public Long create(Course course) {
        return super.create(course);
    }

    @Override
    public void update(Course course) {
        super.update(course);
    }





}  
