package com.yuhao.TeachingServiceSystem.service;

import com.yuhao.TeachingServiceSystem.dao.UserDAO;
import com.yuhao.TeachingServiceSystem.dto.UserDTO;
import com.yuhao.TeachingServiceSystem.model.User;
import com.yuhao.TeachingServiceSystem.security.PasswordEncoder;
import com.yuhao.TeachingServiceSystem.util.BeanMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseService {


    @Autowired
    protected PasswordEncoder passwordEncoder;


    protected <T,K> K toDTO(T model,Class<K> dto) {
        if (model == null){
            return null;
        }
         return BeanMapper.map(model, dto);
    }


    protected <K,T> List<T> toDTOs(List<K> models,Class<T> dto) {
        if (CollectionUtils.isEmpty(models)) {
            return new ArrayList<T>(0);
        }
        List<T> dtos = new ArrayList<T>(models.size());
        for (K model : models) {
            T d = toDTO(model,dto);
            dtos.add(d);
        }
        return dtos;
    }

    protected<K,T> void toModel(K model, T dto) {
        BeanMapper.copy(dto, model);
    }

    @SuppressWarnings("unchecked")
    protected<K,T> List<K> toModels(List<T> dtos) {
        if (CollectionUtils.isEmpty(dtos)) {
            return new ArrayList<K>();
        }
        List<K> models = new ArrayList<K>(dtos.size());
        for (T dto : dtos) {
            Object model = new Object();
            toModel(model, dto);
            models.add((K)model);
        }
        return models;
    }


}
