package org.nexu.outdoors.web.dao;

import org.jongo.MongoCollection;
import org.nexu.outdoors.web.dao.model.CActivity;
import org.nexu.outdoors.web.dao.util.MongoCollectionFactory;
import org.nexu.outdoors.web.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActivityDao {

    @Autowired
    private MongoCollectionFactory mongoCollectionFactory;

    private MongoCollection activityCollection;

    @PostConstruct
    private void init() {
         activityCollection = mongoCollectionFactory.getCollection(CActivity.class);
    }

    public Activity createOrUpdatePost(final Activity activity) {
        CActivity cActivity = new CActivity(activity.getName(), activity.getDescription(), activity.getPicturesUrl(),
                activity.getVideosUrl(), activity.getCreationDate(), activity.getLatestUpdate(), activity.getCreator(),
                activity.getContributors(), activity.getPlayers(), activity.getViewsCounter());
        if(cActivity.getCreationDate() == null) {
            activityCollection.save(cActivity);
        } else {
            activityCollection.update("{_id:#}", activity.getName()).with(cActivity);
        }

        return activity;
    }

    public Activity getActivity(String name) {
        CActivity activity = activityCollection.findOne("{name:#}", name).as(CActivity.class);
        if(activity == null) {
            throw new IllegalStateException("activityNotFound");
        }
        return new Activity(activity.getName(), activity.getDescription(), activity.getPicturesUrl(),
                activity.getVideosUrl(), activity.getCreationDate(), activity.getLatestUpdate(), activity.getCreator(),
                activity.getContributors(),activity.getPlayers(), activity.getViewsCounter());
    }

    public List<Activity> findAll(int offset, int limit) {
        Iterable<CActivity> activities = activityCollection.find().skip(offset).limit(limit).as(CActivity.class);

        List<Activity> resActivityList = new ArrayList<Activity>();
        for(CActivity activity : activities) {
            resActivityList.add(new Activity(activity.getName(), activity.getDescription(), activity.getPicturesUrl(),
                    activity.getVideosUrl(), activity.getCreationDate(), activity.getLatestUpdate(), activity.getCreator(),
                    activity.getContributors(), activity.getPlayers(), activity.getViewsCounter()));
        }

        return resActivityList;
    }

    public Activity delete(String name) {
        Activity activity = getActivity(name);
        activityCollection.remove("{_id:#}", name);
        return activity;
    }


}
