package org.nexu.outdoors.web.resource;


import org.nexu.outdoors.web.model.User;

import javax.ws.rs.core.Response;
import java.util.List;

public interface UserResource {

    List<User> findAll();

    User get(String username);

    User update(String username, User user);

    void create(User user);

    User delete(String username);
}
