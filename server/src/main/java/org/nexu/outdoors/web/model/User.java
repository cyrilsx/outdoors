package org.nexu.outdoors.web.model;


import org.nexu.outdoors.web.dao.model.CUser;
import org.springframework.security.core.GrantedAuthority;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class User extends CUser{
    public User(String username, String email, Date createdAccount, boolean active, boolean locked, String password, File picture, List<String> activities, Collection<GrantedAuthority> authorities) {
        super(username, email, createdAccount, active, locked, password, picture, activities, authorities);
    }
}
