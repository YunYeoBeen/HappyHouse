package com.example.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.dto.User;

@Mapper
public interface UserDAO {

    public User selectUser(String userid);

    public boolean insertUser(User user);

    public boolean deleteUser(String userid);

    public boolean updateUser(User user);

    public int idcheck(String id);

}
