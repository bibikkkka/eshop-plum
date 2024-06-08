package org.tsp.eshopplum.entities;

public class LoginReq {

    private String name;
    private String password;

    public LoginReq(String username, String password) {
        this.name = username;
        this.password = password;
    }

    public LoginReq() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginReq{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
