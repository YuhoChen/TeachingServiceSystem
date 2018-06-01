package com.yuhao.TeachingServiceSystem.web.controller.admin;


import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.dto.NoticeDTO;
import com.yuhao.TeachingServiceSystem.service.NoticeService;
import com.yuhao.TeachingServiceSystem.web.controller.BaseController;


@Controller
@RequestMapping(value = "/admin/notice", produces="text/html;charset=UTF-8")
public class AdminNoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/detail")
    public String detail(Map<String, Object> map, Long id) {
        if (id != null) {
            NoticeDTO dto = noticeService.load(id);
            map.put("n", dto);
        }
        return "admin/notice/notice_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(NoticeDTO dto) throws Exception {
        if (dto.getId() == null) {
            noticeService.create(dto);
        } else {

            NoticeDTO old = noticeService.load(dto.getId());
            old.setTitle(dto.getTitle());
            old.setContent(dto.getContent());
            old.setNoticeTime(dto.getNoticeTime());
            old.setInitiator(dto.getInitiator());
            noticeService.updateAllFields(old);

        }
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Long[] ids) throws IOException {
        noticeService.deleteByIds(ids);
        return ok();
    }

    @RequestMapping(value = "/list")
    public String list(NoticeDTO dto, Boolean search, Map<String, Object> map, Page page) {
        List<NoticeDTO> dtos = noticeService.find(dto, page);
        map.put("list", dtos);
        map.put("query", dto);
        map.put("search", search);
        return "admin/notice/notice_list";
    }

}  
