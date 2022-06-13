package com.example.model.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.dao.UserDAO;
import com.example.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserDAO userdao;

    @Autowired
    public void setUser(UserDAO userdao) {
        this.userdao = userdao;
    }

    @Override
    public User login(String id, String pw) {
        User m = getMember(id);
        System.out.println(passwordEncoder.encode(pw));
        System.out.println(m.getPw());
        System.out.println(m.getPw().equals(passwordEncoder.encode(pw)));
        if (m != null && passwordEncoder.matches(pw, m.getPw())) {
            return m;
        }
        return null;
    }

    @Override
    public User getMember(String id) {
        return userdao.selectUser(id);
    }

    @Override
    @Transactional
    public boolean register(User user) {
        if (getMember(user.getId()) != null) {
            return false;
        }
        String encodedPassword = passwordEncoder.encode(user.getPw());
        user.setPw(encodedPassword);
        return userdao.insertUser(user);
    }

    @Override
    public boolean update(User user) {
        return userdao.updateUser(user);
    }

    @Override
    public boolean delete(String userId) {
        return userdao.deleteUser(userId);
    }

    @Override
    public int idCheck(String id) {
        return userdao.idcheck(id);
    }

    @Override
    public User userInfo(String userid) throws Exception {
        return userdao.selectUser(userid);
    }


}
