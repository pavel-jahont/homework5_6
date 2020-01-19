package com.gmail.jahont.pavel.repository.model;

public class UserInformation {

    private Integer id;
    private String address;
    private String telephone;

    private UserInformation(Builder builder) {
        id = builder.id;
        address = builder.address;
        telephone = builder.telephone;
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

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public static final class Builder {

        private Integer id;
        private String address;
        private String telephone;

        private Builder() {}

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public Builder telephone(String val) {
            telephone = val;
            return this;
        }

        public UserInformation build() {
            return new UserInformation(this);
        }
    }

}