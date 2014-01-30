package org.nexu.outdoors.web.resource;


import com.google.common.collect.Lists;
import org.nexu.outdoors.web.dao.model.CUser;
import org.nexu.outdoors.web.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("user")
@Component
public class UserResourceImpl implements UserResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private org.nexu.outdoors.web.dao.UserDao userDao;

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Override
    public List<User> findAll() {
        return userDao.findAll(0, 10);
    }

    @GET
    @Path("{username}")
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public User get(@PathParam("username") String username) {
        try {
            logger.info("Get detail of {}", username);
            return userDao.getUser(username);
        }catch(IllegalStateException ex) {
            throw new NotFoundException("Entity not found for id: " + username);
        }catch (Exception ex) {
            throw new BadRequestException(ex);
        }
    }

    @POST
    @Path("{username}")
    @Consumes(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public User update(@PathParam("username") String username, User user) {
        return userDao.update(user);
    }

    @PUT
    @Consumes(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public void create(User user) {
        user.setLocked(false);
        user.setActive(false);
        user.setAuthorities(Lists.asList(CUser.Authorities.ROLE_USER.getAuthority(), new String[0]));
        userDao.create(user);
    }

    @DELETE
    @Path("{username}")
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public User delete(@PathParam("username") String username) {
        throw new NotImplementedException();
    }
}
