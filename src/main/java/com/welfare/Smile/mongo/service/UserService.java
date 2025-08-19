package com.welfare.Smile.service;

import com.welfare.Smile.model.User;
import java.util.List;

public interface UserService {
    User registerUser(User user);
    List<User> getAllUsers();
    long getUserCount();
}
