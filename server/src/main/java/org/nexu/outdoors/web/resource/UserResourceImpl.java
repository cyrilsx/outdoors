package org.nexu.outdoors.web.resource;


import org.nexu.outdoors.web.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Response get(@PathParam("username") String username) {
        try {
            logger.info("Get detail of {}", username);
            return Response.ok(userDao.getUser(username), MediaType.APPLICATION_JSON).build();
        }catch(IllegalStateException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for id: " + username).build();
        }
    }

    @POST
    @Path("{username}")
    @Consumes(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public User update(@PathParam("username") String username, User user) {
        return userDao.createOrUpdate(user);
    }

    @PUT
    @Consumes(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public void create(User user) {
        userDao.createOrUpdate(user);
    }

    @DELETE
    @Path("{username}")
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public User delete(@PathParam("username") String username) {
        throw new NotImplementedException();
    }
}
