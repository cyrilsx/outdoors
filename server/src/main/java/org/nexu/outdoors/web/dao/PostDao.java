package org.nexu.outdoors.web.dao;

import org.nexu.outdoors.web.dao.model.CPost;
import org.nexu.outdoors.web.dao.util.MongoCollectionFactory;
import org.nexu.outdoors.web.model.News;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PostDao {

    @Autowired
    private MongoCollectionFactory mongoCollectionFactory;

    private MongoCollection postCollection;

    @PostConstruct
    private void init() {
         postCollection = mongoCollectionFactory.getCollection(CPost.class);
    }

    public News createOrUpdatePost(News post) {
        CPost mongoPost = new CPost(post.getPostId(), post.getTitle(), post.getDescription(), post.getAuthor(), new Date(), new Date());
        if(post.getPostId() == null) {
            postCollection.save(mongoPost);
        } else {
            postCollection.update("{_id:#}", post.getPostId()).with(post);
        }

        return new News(mongoPost.getPostId(), mongoPost.getTitle(), mongoPost.getDescription(), mongoPost.getAuthor(), mongoPost.getCreationDate());
    }

    public News getPost(String postId) {
        //CPost cPost = postCollection.findOne(new ObjectId(postId)).as(CPost.class);
        CPost cPost = postCollection.findOne("{_id:#}", postId).as(CPost.class);
        if(cPost == null) {
            throw new IllegalStateException("postNotFound");
        }
        return new News(cPost.getPostId(), cPost.getTitle(), cPost.getDescription(), cPost.getAuthor(), cPost.getCreationDate());
    }

    public List<News> findAll(int offset, int limit) {
        Iterable<CPost> cPosts = postCollection.find().skip(offset).limit(limit).as(CPost.class);

        List<News> resPostList = new ArrayList<News>();
        for(CPost cPost : cPosts) {
             resPostList.add(new News(cPost.getPostId(), cPost.getTitle(), cPost.getDescription(), cPost.getAuthor(), cPost.getCreationDate()));
        }

        return resPostList;
    }

    public News delete(String postId) {
        News post = getPost(postId);
        postCollection.remove("{_id:#}", postId);
        return post;
    }


}
