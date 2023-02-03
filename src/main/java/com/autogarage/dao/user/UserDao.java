package com.autogarage.dao.user;

import com.autogarage.model.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByUserName(String userName);
}
