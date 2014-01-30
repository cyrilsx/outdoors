package org.nexu.outdoors.web.model;


import org.springframework.security.core.GrantedAuthority;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class User {

    private String username;
    private String email;
    private Date createdAccount;
    private boolean active;
    private boolean locked;
    private String password;

    private File picture;

    private List<String> activities;
    private Collection<String> authorities;

    public User(String username, String email, Date createdAccount, boolean active, boolean locked, String password, File picture, List<String> activities, Collection<String> authorities) {
        this.username = username;
        this.email = email;
        this.createdAccount = createdAccount;
        this.active = active;
        this.locked = locked;
        this.password = password;
        this.picture = picture;
        this.activities = activities;
        this.authorities = authorities;
    }

    public User() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAccount() {
        return createdAccount;
    }

    public void setCreatedAccount(Date createdAccount) {
        this.createdAccount = createdAccount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public File getPicture() {
        return picture;
    }

    public void setPicture(File picture) {
        this.picture = picture;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public Collection<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<String> authorities) {
        this.authorities = authorities;
    }
}
