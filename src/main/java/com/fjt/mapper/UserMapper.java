package com.fjt.mapper;

import com.fjt.pojo.dto.UserQueryDTO;
import com.fjt.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void insert(User user);
    void update(User user);
    void deleteById(Long id);
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
    List<User> search(UserQueryDTO query);
}
