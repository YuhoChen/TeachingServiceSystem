package com.yuhao.TeachingServiceSystem.web.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuhao.TeachingServiceSystem.common.AuthorityModule;
import com.yuhao.TeachingServiceSystem.dto.AuthorityDTO;
import com.yuhao.TeachingServiceSystem.service.AuthorityService;
import com.yuhao.TeachingServiceSystem.util.Page;
import com.yuhao.TeachingServiceSystem.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/authority", produces="text/html;charset=UTF-8")
public class AdminAuthorityController extends BaseController {


    @Autowired
    private AuthorityService authorityService;


    @RequestMapping(value = "/detail")
    public String detail(Map<String, Object> map, Long id, Long roleId) {
        if (id != null) {
            AuthorityDTO dto = authorityService.load(id);
            map.put("n", dto);
        }
        map.put("authorityModules", AuthorityModule.values());
        map.put("roleId", roleId);
        return "admin/authority/authority_detail";
    }


    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, AuthorityDTO dto) throws IOException {
        if (dto.getId() == null) {
            authorityService.save(dto);
        } else {
            AuthorityDTO old = authorityService.load(dto.getId());
            old.setName(dto.getName());
            old.setCode(dto.getCode());
            old.setModule(dto.getModule());
            authorityService.update(old);
        }
        return ok();
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(HttpServletResponse response, Long[] ids) throws IOException {
        authorityService.deleteByIds(ids);
        return ok();
    }

    @RequestMapping(value = "/list")
    public String list(AuthorityDTO dto, Map<String, Object> map, Page page) {
        List<AuthorityDTO> dtos = authorityService.listTops();
        map.put("authorities", dtos);
        map.put("authority", dto);
        return "admin/authority/authority_list";
    }

    @ResponseBody
    @RequestMapping(value = "/searchAuthority")
    public String searchAuthority(HttpServletResponse response, String name) throws IOException {
        List<AuthorityDTO> authorityDTOs = authorityService.list(new AuthorityDTO(), null);
        JSONArray array = new JSONArray();
        for (AuthorityDTO dto : authorityDTOs) {
            JSONObject obj = new JSONObject();
            obj.put("id", dto.getId());
            obj.put("name", dto.getName());
            array.add(obj);
        }
        return array.toJSONString();
    }

}

