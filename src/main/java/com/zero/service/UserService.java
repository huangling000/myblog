package com.zero.service;

import com.zero.po.User;

public interface UserService {
    User checkUser(String username,String password);
}
