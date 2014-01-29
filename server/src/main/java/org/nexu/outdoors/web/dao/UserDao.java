package org.nexu.outdoors.web.dao;

import org.jongo.MongoCollection;
import org.nexu.outdoors.web.dao.model.CUser;
import org.nexu.outdoors.web.dao.util.MongoCollectionFactory;
import org.nexu.outdoors.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component("authenticationProvider")
public class UserDao implements UserDetailsService {

    @Autowired
    private MongoCollectionFactory mongoCollectionFactory;

    private MongoCollection userCollection;

    @PostConstruct
    private void init() {
        userCollection = mongoCollectionFactory.getCollection(CUser.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CUser user = userCollection.findOne("{_id:#}", username).as(CUser.class);
        if(user == null) {
            throw new UsernameNotFoundException("user " + username + " wasn't found!");
        }
        return user;
    }

    public User createOrUpdate(User user) {
        CUser mongoUser = new CUser(user.getUsername(), user.getEmail(),user.getCreatedAccount(), user.isActive(), user.isLocked(),
                user.getPassword(), user.getPicture(), user.getActivities(), user.getAuthorities());
        if(mongoUser.getCreatedAccount() == null) {
            userCollection.save(mongoUser);
        } else {
            userCollection.update("{_id:#}", user.getUsername()).with(mongoUser);
        }

        return user;
    }

    public User getUser(String username) {
        //CUser CUser = UserCollection.findOne(new ObjectId(postId)).as(CUser.class);
        CUser user = userCollection.findOne("{_id:#}", username).as(CUser.class);
        if(user == null) {
            throw new IllegalStateException("user " + username + " wasn't found!");
        }
        return new User(user.getUsername(), user.getEmail(),user.getCreatedAccount(), user.isActive(), user.isLocked(),
                user.getPassword(), user.getPicture(), user.getActivities(), user.getAuthorities());
    }

    public List<User> findAll(int offset, int limit) {
        Iterable<CUser> users = userCollection.find().skip(offset).limit(limit).as(CUser.class);

        List<User> resPostList = new ArrayList<User>();
        for(CUser user : users) {
            resPostList.add(new User(user.getUsername(), user.getEmail(),user.getCreatedAccount(), user.isActive(), user.isLocked(),
                    user.getPassword(), user.getPicture(), user.getActivities(), user.getAuthorities()));
        }

        return resPostList;
    }
}
