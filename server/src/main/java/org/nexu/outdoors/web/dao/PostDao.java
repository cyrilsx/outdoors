package org.nexu.outdoors.web.dao;

import org.nexu.outdoors.web.dao.model.CPost;
import org.nexu.outdoors.web.dao.util.MongoCollectionFactory;
import org.nexu.outdoors.web.model.Post;
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

    public Post createOrUpdatePost(Post post) {
        CPost mongoPost = new CPost(post.getPostId(), post.getTitle(), post.getDescription(), post.getAuthor(), new Date(), null);
        if(post.getPostId() == null) {
            postCollection.save(mongoPost);
        } else {
            postCollection.update("{_id:#}", post.getPostId()).with(post);
        }

        return new Post(mongoPost.getPostId(), mongoPost.getTitle(), mongoPost.getDescription(), mongoPost.getAuthor());
    }

    public Post getPost(String postId) {
        //CPost cPost = postCollection.findOne(new ObjectId(postId)).as(CPost.class);
        CPost cPost = postCollection.findOne("{_id:#}", postId).as(CPost.class);
        if(cPost == null) {
            throw new IllegalStateException("postNotFound");
        }
        return new Post(cPost.getPostId(), cPost.getTitle(), cPost.getDescription(), cPost.getAuthor());
    }

    public List<Post> findAll(int offset, int limit) {
        Iterable<CPost> cPosts = postCollection.find().skip(offset).limit(limit).as(CPost.class);

        List<Post> resPostList = new ArrayList<Post>();
        for(CPost cPost : cPosts) {
             resPostList.add(new Post(cPost.getPostId(), cPost.getTitle(), cPost.getDescription(), cPost.getAuthor()));
        }

        return resPostList;
    }

    public Post delete(String postId) {
        Post post = getPost(postId);
        postCollection.remove("{_id:#}", postId);
        return post;
    }


}
