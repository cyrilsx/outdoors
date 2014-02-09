package org.nexu.outdoors.web.dao.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.nexu.outdoors.web.dao.util.MongoModel;

import java.util.Date;
import java.util.List;

@MongoModel("activity")
public class CActivity {

    private static final String NAME = "_id";
    private static final String DESCRIPTION = "description";
    private static final String PICTURES_URL = "pictures_url";
    private static final String VIDEOS_URL = "videos_url";
    private static final String LATEST_UPDATE = "latest_update";
    private static final String CREATION_DATE = "creation_date";
    private static final String CREATOR = "creator";
    private static final String CONTRIBUTORS = "contributors";
    private static final String PLAYERS = "players";
    private static final String VIEWS_COUNTER = "views_counter";
    private static final String ACTIVITY_LINK= "link";

    private final String name;
    private final String description;
    private final List<String> picturesUrl;
    private final List<String> videosUrl;

    private final Date creationDate;
    private final Date latestUpdate;

    private final String creator;
    private final List<String> contributors;

    private final List<String> players;

    private final int viewsCounter;
    private final String activityLink;

    @JsonCreator
    public CActivity(@JsonProperty(NAME) String name, @JsonProperty(DESCRIPTION) String description, @JsonProperty(PICTURES_URL)
    List<String> picturesUrl, @JsonProperty(VIDEOS_URL) List<String> videosUrl, @JsonProperty(CREATION_DATE) Date creationDate,
                     @JsonProperty(LATEST_UPDATE) Date latestUpdate, @JsonProperty(CREATOR) String creator, @JsonProperty(CONTRIBUTORS) List<String> contributors,
                     @JsonProperty(PLAYERS) List<String> players, @JsonProperty(VIEWS_COUNTER) int viewsCounter, @JsonProperty(ACTIVITY_LINK) String activityLink) {
        this.name = name;
        this.description = description;
        this.picturesUrl = picturesUrl;
        this.videosUrl = videosUrl;
        this.creationDate = creationDate;
        this.latestUpdate = latestUpdate;
        this.creator = creator;
        this.contributors = contributors;
        this.players = players;
        this.viewsCounter = viewsCounter;
        this.activityLink = activityLink;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getPicturesUrl() {
        return picturesUrl;
    }

    public List<String> getVideosUrl() {
        return videosUrl;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLatestUpdate() {
        return latestUpdate;
    }

    public String getCreator() {
        return creator;
    }

    public List<String> getContributors() {
        return contributors;
    }

    public List<String> getPlayers() {
        return players;
    }

    public int getViewsCounter() {
        return viewsCounter;
    }

    public String getActivityLink() {
        return activityLink;
    }
}
