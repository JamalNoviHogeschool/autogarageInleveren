package com.autogarage.model.dto.user;

import com.autogarage.constants.AppsConstants;
import com.autogarage.model.domain.user.User;

public class UserDTO {

    private Integer userID;

    private String userName;

    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private String password;

    private AppsConstants.UserRole userRole;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.userID = user.getUserID();
        this.userName = user.getUserName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.mobile = user.getMobile();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userRole = user.getUserRole();
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppsConstants.UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(AppsConstants.UserRole userRole) {
        this.userRole = userRole;
    }
}
