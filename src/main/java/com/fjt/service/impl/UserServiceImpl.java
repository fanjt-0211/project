package com.fjt.service.impl;

import com.fjt.mapper.UserMapper;
import com.fjt.pojo.dto.LoginDTO;
import com.fjt.pojo.dto.UserQueryDTO;
import com.fjt.pojo.entity.User;
import com.fjt.pojo.vo.UserVO;
import com.fjt.service.UserService;
import com.fjt.utils.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO login(LoginDTO loginDTO) {
        User user = userMapper.findByUsername(loginDTO.getUsername());
        if (user != null && MD5Utils.encrypt(loginDTO.getPassword()).equals(user.getPassword())) {
            return convertToVO(user);
        }
        return null;
    }

    @Override
    public void add(User user) {
        user.setStatus(1);
        user.setPassword(MD5Utils.encrypt(user.getPassword()));
        userMapper.insert(user);
    }
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public UserVO findById(Long id) {
        User user = userMapper.findById(id);
        return user != null ? convertToVO(user) : null;
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public List<UserVO> search(UserQueryDTO query) {
        return userMapper.search(query).stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    private UserVO convertToVO(User user) {
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);
        vo.setRoleName(user.getRole() == 1 ? "管理员" : "普通用户");
        return vo;
    }
}
