package com.yuhao.TeachingServiceSystem.service;

import com.alibaba.fastjson.JSONObject;
import com.yuhao.TeachingServiceSystem.dao.UserDAO;
import com.yuhao.TeachingServiceSystem.dto.UserDTO;
import com.yuhao.TeachingServiceSystem.exception.BizException;
import com.yuhao.TeachingServiceSystem.model.User;
import com.yuhao.TeachingServiceSystem.util.BeanMapper;
import com.yuhao.TeachingServiceSystem.util.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class UserService extends BaseService{

    @Autowired
    private UserDAO userDAO;



    public List<UserDTO> find(UserDTO query, Page page) {
        List<User> models = userDAO.find(query, page);
        List<UserDTO> dtos = toDTOs(models,UserDTO.class);
        return dtos;
    }

    public UserDTO findOne(UserDTO query) {
        User model = userDAO.findOne(query);
        return toDTO(model,UserDTO.class);
    }

    public Long create(UserDTO dto) {
        User model = new User();
        toModel(model, dto);
        Long id = userDAO.create(model);
        return id;
    }


    public void updateAllFields(UserDTO dto) {

        User model = userDAO.load(dto.getId());
        toModel(model, dto);

        userDAO.update(model);

    }

    public UserDTO load(Long id) {
        User model = userDAO.load(id);
        return toDTO(model,UserDTO.class);
    }


    @Transactional
    public void resetPassword(String newPassword, Long userId) {
        User user = userDAO.load(userId);
        if ( user == null ){
            throw new BizException("该用户不存在");
        }
        String newPwd = passwordEncoder.encodePassword(newPassword, user.getUsername());
        user.setPassword(newPwd);
        userDAO.update(user);
    }

    @Transactional
    public void changePassword(Long userId, String oldPassword, String newPassword, String confirmPassword) {
        User user = userDAO.load(userId);
        if (!passwordEncoder.encodePassword(oldPassword, user.getUsername()).equals(user.getPassword())) {
            throw new BizException("旧密码不正确");
        }
        if (StringUtils.isBlank(newPassword) || !newPassword.equals(confirmPassword)) {
            throw new BizException("两次新密码不匹配");
        }
        this.resetPassword(newPassword, userId);
    }


    public JSONObject toJson(UserDTO user,boolean isSuccess){

        ArrayList<Field> fields=UserDTO.getFieldList();

        JSONObject userJson=new JSONObject();

        for (Field field:fields) {
            PropertyDescriptor pd = null;
            try {
                pd = new PropertyDescriptor(field.getName(), user.getClass());
                Method getMethod = pd.getReadMethod();
                Object o = getMethod.invoke(user);
                userJson.put(field.getName(),o);
            } catch (IntrospectionException e) {

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        userJson.put("success",isSuccess);

        return userJson;
    }

    public void deleteByIds(Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                if (id == 1) {
                    throw new RuntimeException("禁止删除管理员");
                }
                 userDAO.deleteById(id);

            }
        }
    }


    public UserDTO toUserDTO(JSONObject data){

        UserDTO user=new UserDTO();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            PropertyDescriptor pd = null;
            try {
                pd = new PropertyDescriptor(entry.getKey().toString(), user.getClass());

                Method getMethod = pd.getWriteMethod();

                if (entry.getValue().getClass().equals(Integer.class)){
                    Integer integer=(Integer)entry.getValue();
                    entry.setValue(new Long((integer.longValue())));
                }

                getMethod.invoke(user,pd.getPropertyType().cast(entry.getValue()));



            }catch (Exception e){
                e.printStackTrace();
            }


        }
        return user;
    }


    private void toModel(User model, UserDTO dto) {
        BeanMapper.copy(dto, model);
    }


}
