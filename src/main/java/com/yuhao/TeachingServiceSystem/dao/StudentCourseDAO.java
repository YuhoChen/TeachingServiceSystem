package com.yuhao.TeachingServiceSystem.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.model.StudentCourse;
import com.yuhao.TeachingServiceSystem.dto.StudentCourseDTO;


@Repository
public class StudentCourseDAO extends BaseDAO<StudentCourse>{


    public List<StudentCourse> find(StudentCourseDTO dto,Page page){

            StringBuilder hql = new StringBuilder();
            List<Object> paras = new ArrayList<Object>();
            hql.append("From StudentCourse n where 1 = 1 ");

                    if(dto.getId()!=null){
                         hql.append(" and n.id = ? ");
                         paras.add(dto.getId());
                     }
                    if(dto.getStudentNumber()!=null){
                        if (StringUtils.isNotBlank(dto.getStudentNumber().getStudentNumber())) {
                            hql.append(" and n.studentNumber.studentNumber = ? ");
                            paras.add(dto.getStudentNumber().getStudentNumber());
                        }
                    }

                    if(dto.getCourseNumber()!=null){
                        if (StringUtils.isNotBlank(dto.getCourseNumber().getCourseNumber())) {
                            hql.append(" and n.courseNumber.courseNumber = ? ");
                            paras.add(dto.getCourseNumber().getCourseNumber());
                        }
                    }
                    if(dto.getScore()!=null){
                         hql.append(" and n.score = ? ");
                         paras.add(dto.getScore());
                     }

            return super.find(hql.toString(), paras.toArray(), page);
    }




    public StudentCourse findOne(StudentCourseDTO dto){
        Page page = new Page();
        page.setPageSize(1);
        page.setPage(1);

        List<StudentCourse> list = this.find(dto, page);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);

    }

    @Override
    public Long create(StudentCourse studentCourse) {
        return super.create(studentCourse);
    }

    @Override
    public void update(StudentCourse studentCourse) {
        super.update(studentCourse);
    }





}  
