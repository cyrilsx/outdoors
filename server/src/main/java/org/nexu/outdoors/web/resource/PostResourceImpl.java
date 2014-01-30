package org.nexu.outdoors.web.resource;


import org.nexu.outdoors.web.dao.PostDao;
import org.nexu.outdoors.web.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("news")
@Component
public class PostResourceImpl implements PostResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PostDao postDao;


    public PostResourceImpl() {
        logger.info("create PostResourceImpl");
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    //@Path("all/{start}/{end}")
    @Override
    public List<News> findAll() {
        logger.info("findAll called");
        //return postDao.findAll(start, end);
        return postDao.findAll(0, 10);
    }

    @GET
    @Path("{news_id}")
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response get(@PathParam("news_id") String id) {
        try {
            logger.info("Get detail of {}", id);
            return Response.ok(postDao.getPost(id), MediaType.APPLICATION_JSON).build();
        }catch(IllegalStateException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for id: " + id).build();
        }
    }

    @POST
    @Path("{news_id}")
    @Consumes(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public News update(@PathParam("news_id") String id, News post) {
        return postDao.createOrUpdatePost(post);
    }

    @PUT
    @Consumes(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public void create(News post) {
        logger.info("create new post {}", post.getAuthor());
        postDao.createOrUpdatePost(post);
    }

    @DELETE
    @Path("{news_id}")
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RolesAllowed(value = {"ROLE_TRUSTED_CLIENT"})
    @Override
    public News delete(@PathParam("news_id") String id) {
        logger.info("remove post with id {}", id);
        return postDao.delete(id);
    }
}
