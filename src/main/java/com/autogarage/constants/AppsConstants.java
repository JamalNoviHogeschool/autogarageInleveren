package com.autogarage.constants;

import org.apache.commons.lang3.StringUtils;

public class AppsConstants {

    public enum ResponseStatus {
        SUCCESS, FAILED;

        public static ResponseStatus resolveStatus(String statusStr) {
            ResponseStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ResponseStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }
    }

    public enum YesNo {
        Y("Yes"),
        N("No");

        private String strVal;

        YesNo(String strVal) {
            this.strVal = strVal;
        }

        public static YesNo resolver(String val) {
            YesNo matchingVal = null;
            if (val != null) {
                matchingVal = YesNo.valueOf(val);
            }
            return matchingVal;
        }

        public String getStrVal() {
            return strVal;
        }
    }

    public enum Status {
        ACT("Active"),
        INA("Inactive");

        private String description;

        Status(String description) {
            this.description = description;
        }

        public static Status resolveStatus(String statusStr) {
            Status matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = Status.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum UserRole {
        ADMIN("ADMIN"),
        ENGINEER("Engineer"),
        USER("USER");

        private String description;

        UserRole(String description) {
            this.description = description;
        }

        public static UserRole resolveUserRole(String userRoleStr) {
            UserRole matchingUserRole = null;
            if (StringUtils.isNotEmpty(userRoleStr)) {
                matchingUserRole = UserRole.valueOf(userRoleStr.trim());
            }
            return matchingUserRole;
        }

        public String getDescription() {
            return description;
        }
    }
}
