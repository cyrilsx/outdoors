package org.nexu.outdoors.web.dao.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import org.jongo.marshall.jackson.oid.Id;
import org.nexu.outdoors.web.dao.util.MongoModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@MongoModel("user")
public class CUser implements UserDetails {

    public enum Authorities implements GrantedAuthority {
        ROLE_ADMIN,
        ROLE_USER;


        public String getAuthority() {
            return this.name();
        }

        public static Collection<GrantedAuthority> fromStr(Collection<String> args) {
            Collection<GrantedAuthority> resCollection = Lists.newArrayList();
            for(String value : args) {
                resCollection.add(Authorities.valueOf(value));
           }
            return resCollection;
        }
    }

    private static final String ID = "_id";
    private static final String EMAIL = "email";
    private static final String CREATION_DATE = "creation_date";
    private static final String ACTIVE = "active";
    private static final String LOCKED = "locked";
    private static final String PASSWORD = "password";
    private static final String PICTURE = "picture";
    private static final String ACTIVITIES = "activities";
    private static final String AUTHORITIES = "authorities";

    @Id
    private final String username;
    private final String email;
    private final Date createdAccount;
    private final boolean active;
    private final boolean locked;
    private final String password;

    private final File picture;

    private final List<String> activities;
    private final Collection<String> authorities;

    @JsonCreator
    public CUser(@JsonProperty(ID) String username, @JsonProperty(EMAIL) String email, @JsonProperty(CREATION_DATE) Date createdAccount,
                 @JsonProperty(ACTIVE) boolean active, @JsonProperty(LOCKED) boolean locked, @JsonProperty(PASSWORD) String password, @JsonProperty(PICTURE) File picture,
                 @JsonProperty(ACTIVITIES) List<String> activities, @JsonProperty(AUTHORITIES) Collection<String> authorities) {
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


    @JsonProperty(ID)
    public String getUsername() {
        return username;
    }

    @JsonProperty(EMAIL)
    public String getEmail() {
        return email;
    }

    @JsonProperty(CREATION_DATE)
    public Date getCreatedAccount() {
        return createdAccount;
    }

    @JsonProperty(ACTIVE)
    public boolean isActive() {
        return active;
    }

    @JsonProperty(LOCKED)
    public boolean isLocked() {
        return locked;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Authorities.fromStr(getStrAuthorities());
    }

    @JsonProperty(AUTHORITIES)
    public Collection<String> getStrAuthorities() {
        return authorities;
    }

    @Override
    @JsonProperty(PASSWORD)
    public String getPassword() {
        return password;
    }

    @JsonProperty(PICTURE)
    public File getPicture() {
        return picture;
    }

    @JsonProperty(ACTIVITIES)
    public List<String> getActivities() {
        return activities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public String toString() {
        return "CUser{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", createdAccount=" + createdAccount +
                ", active=" + active +
                ", locked=" + locked +
                ", password='" + password + '\'' +
                ", picture=" + picture +
                ", activities=" + activities +
                ", authorities=" + authorities +
                '}';
    }
}
