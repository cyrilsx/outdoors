package org.nexu.outdoors.web.resource;


import org.nexu.outdoors.web.dao.ActivityDao;
import org.nexu.outdoors.web.model.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Path("activity")
@Component
public class ActivityResourceImpl implements ActivityResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ActivityDao activityDao;

    @Override
    @GET
    @Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Activity> findAll() {
        return activityDao.findAll(0, 10);
    }

    @GET
    @Path("{name}")
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Activity get(@PathParam("name") String name) {
        return activityDao.getActivityByLink(name);
    }
    @Override
    public Activity update(String name, Activity activity) {
        return activityDao.createOrUpdatePost(activity);
    }

    @PUT
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public void create(Activity activity) {
        activity.setLatestUpdate(new Date());
        activity.setViewsCounter(0);
        activity.setActivityLink(activity.getName().replace(' ', '_'));
        activityDao.createOrUpdatePost(activity);
    }

    @DELETE
    @Path("{name}")
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Activity delete(@QueryParam("name") String name) {
        logger.info("remove activity with name {}", name);
        return activityDao.delete(name);
    }
}
