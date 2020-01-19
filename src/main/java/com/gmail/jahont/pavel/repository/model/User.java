package com.gmail.jahont.pavel.repository.model;

public class User {

    private Integer id;
    private String userName;
    private String password;
    private Boolean isActive;
    private Integer userGroupId;
    private Integer age;

    private User(Builder builder) {
        id = builder.id;
        userName = builder.userName;
        password = builder.password;
        isActive = builder.isActive;
        userGroupId = builder.userGroupId;
        age = builder.age;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", userGroupId=" + userGroupId +
                ", age=" + age +
                '}';
    }

    public static final class Builder {

        private Integer id;
        private String userName;
        private String password;
        private Boolean isActive;
        private Integer userGroupId;
        private Integer age;

        private Builder() {}

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder isActive(Boolean val) {
            isActive = val;
            return this;
        }

        public Builder userGroupId(Integer val) {
            userGroupId = val;
            return this;
        }

        public Builder age(Integer val) {
            age = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}