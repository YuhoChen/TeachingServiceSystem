package com.yuhao.TeachingServiceSystem.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.model.Exam;
import com.yuhao.TeachingServiceSystem.dto.ExamDTO;


@Repository
public class ExamDAO extends BaseDAO<Exam>{


    public List<Exam> find(ExamDTO dto,Page page){

            StringBuilder hql = new StringBuilder();
            List<Object> paras = new ArrayList<Object>();
            hql.append("From Exam n where 1 = 1 ");

                    if(dto.getId()!=null){
                         hql.append(" and n.id = ? ");
                         paras.add(dto.getId());
                     }
                    if(dto.getCourseNumber()!=null){
                        if (dto.getCourseNumber().getId() != null) {
                            hql.append(" and n.courseNumber.id = ? ");
                            paras.add(dto.getCourseNumber().getId());
                        }
                    }

                    if(dto.getExamTime()!=null){
                         hql.append(" and n.examTime = ? ");
                         paras.add(dto.getExamTime());
                     }
                    if (StringUtils.isNotBlank(dto.getExamPlace())) {
                        hql.append(" and n.examPlace = ? ");
                        paras.add(dto.getExamPlace());
                    }

                    if(dto.getExamCapacity()!=null){
                         hql.append(" and n.examCapacity = ? ");
                         paras.add(dto.getExamCapacity());
                     }
                    if (StringUtils.isNotBlank(dto.getInvigilator())) {
                        hql.append(" and n.invigilator = ? ");
                        paras.add(dto.getInvigilator());
                    }

                    if (StringUtils.isNotBlank(dto.getRemark())) {
                        hql.append(" and n.remark = ? ");
                        paras.add(dto.getRemark());
                    }


            hql.append(" order by n.id desc ");
            return super.find(hql.toString(), paras.toArray(), page);
    }

    public Exam findOne(ExamDTO dto){
        Page page = new Page();
        page.setPageSize(1);
        page.setPage(1);

        List<Exam> list = this.find(dto, page);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);

    }

    @Override
    public Long create(Exam exam) {
        return super.create(exam);
    }

    @Override
    public void update(Exam exam) {
        super.update(exam);
    }





}  
