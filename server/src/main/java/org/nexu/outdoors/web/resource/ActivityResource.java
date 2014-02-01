package org.nexu.outdoors.web.resource;

import org.nexu.outdoors.web.model.Activity;
import org.nexu.outdoors.web.model.News;

import javax.ws.rs.core.Response;
import java.util.List;


public interface ActivityResource {


    List<Activity> findAll();

    Activity get(String name);

    Activity update(String name, Activity activity);

    void create(Activity activity);

    Activity delete(String name);


}
