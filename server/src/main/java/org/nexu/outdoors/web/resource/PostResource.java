package org.nexu.outdoors.web.resource;

import org.nexu.outdoors.web.model.News;

import javax.ws.rs.core.Response;
import java.util.List;


public interface PostResource {


    List<News> findAll();

    Response get(String id);

    News update(String id, News post);

    void create(News post);

    News delete(String id);


}
