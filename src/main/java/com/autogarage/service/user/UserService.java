package com.autogarage.service.user;

import com.autogarage.dao.user.UserDao;
import com.autogarage.exception.AppsException;
import com.autogarage.model.domain.user.User;
import com.autogarage.model.dto.user.UserDTO;
import com.autogarage.model.dto.user.UserPasswordChangeDTO;
import com.autogarage.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDao.findByUserName(username);

        if (user == null) {
            LOG.error("ERROR : User not found in database : {}", username);
            throw new UsernameNotFoundException("User not found in database");
        } else {
            LOG.info("INFO : User found in database : {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getDescription()));

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                authorities
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserDTO registerUser(UserDTO registerUserDTO) throws AppsException {
        User user = this.userDao.findByUserName(registerUserDTO.getUserName());

        if (user != null) {
            LOG.error("ERROR : Customer already exists : {}", user.getUserName());
            throw new AppsException("User already exits");
        } else {
            user = new User();

            user.setUserName(registerUserDTO.getUserName());
            user.setFirstName(registerUserDTO.getFirstName());
            user.setLastName(registerUserDTO.getLastName());
            user.setMobile(registerUserDTO.getMobile());
            user.setUserRole(registerUserDTO.getUserRole());
            user.setEmail(registerUserDTO.getEmail());

            user.setPassword(this.generateEncodedPassword(registerUserDTO.getPassword()));

            user = this.userDao.saveAndFlush(user);

            return new UserDTO(user);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserDTO changeUserPassword(UserPasswordChangeDTO passwordChangeDTO) throws AppsException {
        if (!this.userDao.existsById(passwordChangeDTO.getUserID())) {
            throw new AppsException("Can't find User");
        } else {
            User user = this.userDao.getReferenceById(passwordChangeDTO.getUserID());

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            String oldPassword = passwordChangeDTO.getOldPassword();
            String existingPassword = user.getPassword();

            if (passwordEncoder.matches(oldPassword, existingPassword)) {
                String encodedPassword = this.generateEncodedPassword(passwordChangeDTO.getNewPassword());

                user.setPassword(encodedPassword);
                user = this.userDao.saveAndFlush(user);

                return new UserDTO(user);
            } else {
                throw new AppsException("Password mismatch");
            }
        }
    }

    public String generateEncodedPassword(String plainPassword) throws AppsException {
        return PasswordUtil.generateEncodedPassword(new BCryptPasswordEncoder(), plainPassword);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserDTO getUserByID(Integer userID) throws AppsException {
        User user = this.userDao.getReferenceById(userID);
        return new UserDTO(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserDTO resetUserPassword(Integer userID) throws AppsException {
        User user = this.userDao.getReferenceById(userID);

        if (user == null) {
            throw new AppsException("Can't find User");
        } else {
            //Generate random password and send to user email
            String email = user.getEmail();
//            String resetPassword =

            user = this.userDao.saveAndFlush(user);

            return new UserDTO(user);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserDTO updateUser(UserDTO userUpdateDTO) throws AppsException {

        if (!this.userDao.existsById(userUpdateDTO.getUserID())) {
            LOG.error("ERROR : Can't find User: {}", userUpdateDTO.getUserName());
            throw new AppsException("Can't find User");
        } else {
            User user = this.userDao.getReferenceById(userUpdateDTO.getUserID());

            user.setUserName(userUpdateDTO.getUserName());
            user.setFirstName(userUpdateDTO.getFirstName());
            user.setLastName(userUpdateDTO.getLastName());
            user.setMobile(userUpdateDTO.getMobile());
            user.setUserRole(userUpdateDTO.getUserRole());

            user = this.userDao.saveAndFlush(user);

            return new UserDTO(user);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<UserDTO> getAllUsers() throws AppsException {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> users = this.userDao.findAll();

        for (User user : users) {
            userDTOList.add(new UserDTO(user));
        }

        return userDTOList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(Integer userID) throws AppsException {
        if (this.userDao.existsById(userID)) {
            this.userDao.deleteById(userID);
        } else {
            throw new AppsException("No user found");
        }
    }
}
