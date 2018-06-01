package com.yuhao.TeachingServiceSystem.service;

import java.util.List;

import com.yuhao.TeachingServiceSystem.dao.AuthorityDAO;
import com.yuhao.TeachingServiceSystem.dao.RoleAuthorityDAO;
import com.yuhao.TeachingServiceSystem.dto.AuthorityDTO;
import com.yuhao.TeachingServiceSystem.model.Authority;
import com.yuhao.TeachingServiceSystem.model.RoleAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;


@Service
public class RoleAuthorityService extends BaseService{

    @Autowired
    private RoleAuthorityDAO roleAuthorityDAO;
    @Autowired
    private AuthorityDAO authorityDAO;

    public List<AuthorityDTO> findByAuthoritiesByRoleId(Long roleId) {
        List<RoleAuthority> roleAuthorities = roleAuthorityDAO.findByRoleId(roleId);
        List<AuthorityDTO> authorities = Lists.newArrayList();
        for (RoleAuthority roleAuthority : roleAuthorities) {
            authorities.add(toDTO(authorityDAO.load(roleAuthority.getAuthorityId()),AuthorityDTO.class));
        }
        return authorities;
    }

    @Transactional
    public void update(Long roleId, Long[] authorityIds) {
        roleAuthorityDAO.deleteByRoleId(roleId);

        for (Long id : authorityIds) {
            RoleAuthority ra = new RoleAuthority();
            ra.setRoleId(roleId);
            ra.setAuthorityId(id);
            roleAuthorityDAO.create(ra);
        }
    }

}
