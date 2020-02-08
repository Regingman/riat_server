package com.riatServer.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.riatServer.repo.UsersRepo;
import com.riatServer.service.UserService;

@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UsersRepo usersRepo;

}
