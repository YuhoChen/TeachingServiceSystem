package com.yuhao.TeachingServiceSystem.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.model.Student;
import com.yuhao.TeachingServiceSystem.dto.StudentDTO;


@Repository
public class StudentDAO extends BaseDAO<Student>{


    public List<Student> find(StudentDTO dto,Page page){

            StringBuilder hql = new StringBuilder();
            List<Object> paras = new ArrayList<Object>();
            hql.append("From Student n where 1 = 1 ");

                    if(dto.getId()!=null){
                         hql.append(" and n.id = ? ");
                         paras.add(dto.getId());
                     }
                    if (StringUtils.isNotBlank(dto.getStudentNumber())) {
                        hql.append(" and n.studentNumber = ? ");
                        paras.add(dto.getStudentNumber());
                    }

                    if (StringUtils.isNotBlank(dto.getName())) {
                        hql.append(" and n.name = ? ");
                        paras.add(dto.getName());
                    }

                    if (StringUtils.isNotBlank(dto.getSex())) {
                        hql.append(" and n.sex = ? ");
                        paras.add(dto.getSex());
                    }

                    if (StringUtils.isNotBlank(dto.getIdCard())) {
                        hql.append(" and n.idCard = ? ");
                        paras.add(dto.getIdCard());
                    }

                    if(dto.getBirth()!=null){
                         hql.append(" and n.birth = ? ");
                         paras.add(dto.getBirth());
                     }
                    if (StringUtils.isNotBlank(dto.getNativePlace())) {
                        hql.append(" and n.nativePlace = ? ");
                        paras.add(dto.getNativePlace());
                    }

                    if (StringUtils.isNotBlank(dto.getEmail())) {
                        hql.append(" and n.email = ? ");
                        paras.add(dto.getEmail());
                    }

                    if (StringUtils.isNotBlank(dto.getPhone())) {
                        hql.append(" and n.phone = ? ");
                        paras.add(dto.getPhone());
                    }

                    if (StringUtils.isNotBlank(dto.getCollege())) {
                        hql.append(" and n.college = ? ");
                        paras.add(dto.getCollege());
                    }

                    if (StringUtils.isNotBlank(dto.getMyClass())) {
                        hql.append(" and n.myClass = ? ");
                        paras.add(dto.getMyClass());
                    }

                    if (StringUtils.isNotBlank(dto.getPassword())) {
                        hql.append(" and n.password = ? ");
                        paras.add(dto.getPassword());
                    }


            hql.append(" order by n.id desc ");
            return super.find(hql.toString(), paras.toArray(), page);
    }

    public Student findOne(StudentDTO dto){
        Page page = new Page();
        page.setPageSize(1);
        page.setPage(1);

        List<Student> list = this.find(dto, page);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);

    }

    @Override
    public Long create(Student student) {
        return super.create(student);
    }

    @Override
    public void update(Student student) {
        super.update(student);
    }





}  
