package com.example.model.service;

import com.example.model.dto.User;

public interface UserService {
    public User login(String id, String pw);

    public User getMember(String id);

    public boolean register(User user);

    public boolean update(User user);

    public boolean delete(String userId);

    public int idCheck(String id);

    public User userInfo(String userid) throws Exception;

}
