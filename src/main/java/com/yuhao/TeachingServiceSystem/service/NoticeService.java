package com.yuhao.TeachingServiceSystem.service;


import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.model.Notice;
import com.yuhao.TeachingServiceSystem.dto.NoticeDTO;
import com.yuhao.TeachingServiceSystem.dao.NoticeDAO;


@Service
public class NoticeService extends BaseService{

    @Autowired
    private NoticeDAO noticeDAO;

    public List<NoticeDTO> find(NoticeDTO query,Page page){

        List<Notice> models = noticeDAO.find(query, page);
        List<NoticeDTO> dtos = toDTOs(models,NoticeDTO.class);
        return dtos;

    }
     public List<NoticeDTO> findAll() {
         return this.find(new NoticeDTO(), null);
     }

     public List<NoticeDTO> findAll(NoticeDTO query) {
          return this.find(query, null);
     }

     public List<NoticeDTO> find(Page page) {
           return this.find(new NoticeDTO(), page);
     }

     public List<NoticeDTO> find(int count) {
            Page page = new Page();
            page.setPage(1);
            page.setPageSize(count);
            return this.find(new NoticeDTO(), page);
     }

     public Long create(NoticeDTO dto) {
             Notice model = new Notice();
             toModel(model, dto);
             return noticeDAO.create(model);
     }

     public NoticeDTO load(Long id) {
             Notice model = noticeDAO.load(id);
              return toDTO(model,NoticeDTO.class);
     }

      public void updateAllFields(NoticeDTO dto) {
              Notice model = noticeDAO.load(dto.getId());
              toModel(model, dto);
               noticeDAO.update(model);
      }

      public void deleteByIds(Long[] ids) {
              if (ids != null) {
               for (Long id : ids) {
                    noticeDAO.deleteById(id);
                }
               }
      }

      public void deleteById(Long id) {
           this.deleteByIds(new Long[] {id});
      }

      public NoticeDTO findOne(NoticeDTO query) {
            Notice model = noticeDAO.findOne(query);
            return toDTO(model,NoticeDTO.class);
      }

}  
