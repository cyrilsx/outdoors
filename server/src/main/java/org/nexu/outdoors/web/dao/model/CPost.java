package org.nexu.outdoors.web.dao.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.Id;
import org.nexu.outdoors.web.dao.util.MongoModel;

import java.util.Date;

@MongoModel("post")
public class CPost {

    private static final String ID = "_id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String LAST_UPDATE = "last_update";
    private static final String CREATION_DATE = "creation_date";
    private static final String AUTHOR = "author";
    private final String title;
    private final String description;
    private final String author;
    private final Date lastUpdate;
    private final Date creationDate;
    @Id
    private String postId;

    @JsonCreator
    public CPost(@JsonProperty(ID) String postId, @JsonProperty(TITLE) String title, @JsonProperty(DESCRIPTION) String description,
                 @JsonProperty(AUTHOR) String author,  @JsonProperty(LAST_UPDATE) Date lastUpdate,  @JsonProperty(CREATION_DATE) Date creationDate) {
        this.author = author;
        this.description = description;
        this.postId = postId;
        this.title = title;
        this.lastUpdate = lastUpdate;
        this.creationDate = creationDate;
    }

    @JsonProperty(ID)
    public String getPostId() {
        return postId;
    }

    @JsonProperty(TITLE)
    public String getTitle() {
        return title;
    }

    @JsonProperty(DESCRIPTION)
    public String getDescription() {
        return description;
    }

    @JsonProperty(AUTHOR)
    public String getAuthor() {
        return author;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
