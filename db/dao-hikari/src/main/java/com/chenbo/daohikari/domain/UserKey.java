package com.chenbo.daohikari.domain;

import javax.persistence.*;

@Table(name = "user")
public class UserKey {
    @Id
    @Column(name = "Host")
    private String host;

    @Id
    @Column(name = "User")
    private String user;

    /**
     * @return Host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return User
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }
}