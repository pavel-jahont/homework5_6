package com.gmail.jahont.pavel.repository.model;

public class UserGroup {

    private Integer id;
    private String name;

    private UserGroup(Builder builder) {
        id = builder.id;
        name = builder.name;
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

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static final class Builder {

        private Integer id;
        private String name;

        private Builder() {}

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public UserGroup build() {
            return new UserGroup(this);
        }
    }

}