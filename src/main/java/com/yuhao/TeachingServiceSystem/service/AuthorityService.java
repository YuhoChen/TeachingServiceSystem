package com.yuhao.TeachingServiceSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.yuhao.TeachingServiceSystem.dao.AuthorityDAO;
import com.yuhao.TeachingServiceSystem.dto.AuthorityDTO;
import com.yuhao.TeachingServiceSystem.dto.ModuleAuthorityDTO;
import com.yuhao.TeachingServiceSystem.model.Authority;
import com.yuhao.TeachingServiceSystem.util.BeanMapper;
import com.yuhao.TeachingServiceSystem.util.Page;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class AuthorityService extends BaseService{

    @Autowired
    private AuthorityDAO authorityDAO;

    public List<AuthorityDTO> list(AuthorityDTO dto, Page page) {
        List<Authority> models = authorityDAO.find(dto, page);
        List<AuthorityDTO> dtos = toDTOs(models,AuthorityDTO.class);
        return dtos;
    }




    public void save(AuthorityDTO dto) {
        Authority model = new Authority();
        toModel(model, dto);
        authorityDAO.create(model);
    }

    private void toModel(Authority model, AuthorityDTO dto) {
        BeanMapper.copy(dto, model);
        if (dto.getParentAuthority() != null && dto.getParentAuthority().getId() != null) {
            model.setParentAuthority(authorityDAO.load(dto.getParentAuthority().getId()));
        }
        if (dto.getParentAuthority() == null || dto.getParentAuthority().getId() == null) {
            model.setParentAuthority(null);
        }
    }


    public AuthorityDTO load(Long id) {
        Authority model = authorityDAO.load(id);
        return toDTO(model,AuthorityDTO.class);
    }

    public void update(AuthorityDTO dto) {
        Authority model = authorityDAO.load(dto.getId());
        toModel(model, dto);
        authorityDAO.update(model);
    }

    public void deleteByIds(Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                authorityDAO.deleteById(id);
            }
        }
    }

    public AuthorityDTO uniqueFind(AuthorityDTO dto) {
        Authority model = authorityDAO.uniqueFind(dto);
        return toDTO(model,AuthorityDTO.class);
    }

    public List<AuthorityDTO> findLikeAuthorities(String name) {
        return toDTOs(authorityDAO.findLikeAuthorities(name),AuthorityDTO.class);
    }

    public List<AuthorityDTO> listTops() {
        return toDTOs(authorityDAO.listTops(),AuthorityDTO.class);
    }

    public List<ModuleAuthorityDTO> findModuleAuthories() {
        List<Authority> authorities = authorityDAO.find(new AuthorityDTO(), null);
        Map<String, List<Authority>> map = new TreeMap<String, List<Authority>>();
        for (Authority a : authorities) {
            String module = a.getModule();
            List<Authority> list = map.get(module);
            if (list == null) {
                list = new ArrayList<Authority>();
                map.put(module, list);
            }
            list.add(a);
        }
        List<ModuleAuthorityDTO> dtos = new ArrayList<ModuleAuthorityDTO>();
        for (String key : map.keySet()) {
            ModuleAuthorityDTO dto = new ModuleAuthorityDTO();
            dto.setModule(key);
            dto.setAuthorities(toDTOs(map.get(key),AuthorityDTO.class));
            dtos.add(dto);
        }
        return dtos;
    }
}
