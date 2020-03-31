package com.riatServer.service.Impl;


import com.riatServer.domain.Role;
import com.riatServer.domain.User;
import com.riatServer.domain.User;
import com.riatServer.exception.ServiceException;
import com.riatServer.repo.RolesRepo;
import com.riatServer.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.riatServer.repo.UsersRepo;
import com.riatServer.service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements  UserService, EntityService<User, Long> {

    @Autowired
    private UsersRepo userRepo;
    @Autowired
    private RolesRepo roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Role.Status.ACTIVE);

        User registeredUser = userRepo.save(user);

        System.out.println("IN register - user: {} successfully registered" + registeredUser);

        return registeredUser;
    }
}
