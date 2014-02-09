package org.nexu.outdoors.web.model;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class Activity {

    private String name;
    private String description;
    private List<String> picturesUrl;
    private List<String> videosUrl;

    private Date creationDate;
    private Date latestUpdate;

    private String creator;
    private List<String> contributors;

    private List<String> players;

    private int viewsCounter;
    private String activityLink;

    public Activity(){}

    public Activity(String name, String description, List<String> picturesUrl, List<String> videosUrl, Date creationDate,
                    Date latestUpdate, String creator, List<String> contributors, List<String> players, int viewsCounter, String activityLink) {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPicturesUrl() {
        return picturesUrl;
    }

    public void setPicturesUrl(List<String> picturesUrl) {
        this.picturesUrl = picturesUrl;
    }

    public List<String> getVideosUrl() {
        return videosUrl;
    }

    public void setVideosUrl(List<String> videosUrl) {
        this.videosUrl = videosUrl;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(Date latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<String> getContributors() {
        return contributors;
    }

    public void setContributors(List<String> contributors) {
        this.contributors = contributors;
    }

    public int getViewsCounter() {
        return viewsCounter;
    }

    public void setViewsCounter(int viewsCounter) {
        this.viewsCounter = viewsCounter;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public String getActivityLink() {
        return activityLink;
    }

    public void setActivityLink(String activityLink) {
        this.activityLink = activityLink;
    }
}
