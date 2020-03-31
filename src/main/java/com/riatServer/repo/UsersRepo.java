package com.riatServer.repo;

import com.riatServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepo extends JpaRepository<User, Long> {
//    @Query("select u.Name from User u")
//    List<User> findByName(@Param("Name") String Name);
}
