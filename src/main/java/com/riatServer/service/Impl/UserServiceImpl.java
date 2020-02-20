package com.riatServer.service.Impl;


import com.riatServer.domain.User;
import com.riatServer.domain.User;
import com.riatServer.exception.ServiceException;
import com.riatServer.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.riatServer.repo.UsersRepo;
import com.riatServer.service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements  UserService, EntityService<User, Long> {

    @Autowired
    private UsersRepo userRepo;
    @Override
    public List<User> getAll() {
        return  userRepo.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        user.setUpdateDate(LocalDateTime.now());
        return userRepo.save(user);
    }

    @Override
    public User create(User user) {
        user.setCreateDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());
        return userRepo.save(user);
    }

    @Override
    public void delete(Long id) throws IOException, ServiceException {
        User user = getById(id);
        userRepo.deleteById(id);
    }
}
