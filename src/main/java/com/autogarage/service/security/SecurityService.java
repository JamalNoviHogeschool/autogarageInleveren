package com.autogarage.service.security;

import com.autogarage.dao.user.UserDao;
import com.autogarage.model.domain.user.User;
import com.autogarage.model.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityService {

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserDTO getUserByMobileUserName(String userName) {
        User user = this.userDao.findByUserName(userName);
        return new UserDTO(user);
    }
}
