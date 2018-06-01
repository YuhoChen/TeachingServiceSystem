package com.yuhao.TeachingServiceSystem.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.model.Notice;
import com.yuhao.TeachingServiceSystem.dto.NoticeDTO;


@Repository
public class NoticeDAO extends BaseDAO<Notice>{


    public List<Notice> find(NoticeDTO dto,Page page){

            StringBuilder hql = new StringBuilder();
            List<Object> paras = new ArrayList<Object>();
            hql.append("From Notice n where 1 = 1 ");

                    if(dto.getId()!=null){
                         hql.append(" and n.id = ? ");
                         paras.add(dto.getId());
                     }
                    if (StringUtils.isNotBlank(dto.getTitle())) {
                        hql.append(" and n.title = ? ");
                        paras.add(dto.getTitle());
                    }

                    if (StringUtils.isNotBlank(dto.getContent())) {
                        hql.append(" and n.content = ? ");
                        paras.add(dto.getContent());
                    }

                    if(dto.getNoticeTime()!=null){
                         hql.append(" and n.noticeTime = ? ");
                         paras.add(dto.getNoticeTime());
                     }
                    if (StringUtils.isNotBlank(dto.getInitiator())) {
                        hql.append(" and n.initiator = ? ");
                        paras.add(dto.getInitiator());
                    }


            hql.append(" order by n.id desc ");
            return super.find(hql.toString(), paras.toArray(), page);
    }

    public Notice findOne(NoticeDTO dto){
        Page page = new Page();
        page.setPageSize(1);
        page.setPage(1);

        List<Notice> list = this.find(dto, page);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);

    }

    @Override
    public Long create(Notice notice) {
        return super.create(notice);
    }

    @Override
    public void update(Notice notice) {
        super.update(notice);
    }





}  
