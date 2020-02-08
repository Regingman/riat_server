package com.riatServer.repo;

import com.riatServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<User, Long> {
}
