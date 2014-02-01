package org.nexu.outdoors.web.dao.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.Id;
import org.nexu.outdoors.web.dao.util.MongoModel;

@MongoModel("article")
public class CArticle {
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String AUTHOR = "author";
    private static final String LAST_UPDATE = "last_update";
    private static final String ACTIVITY = "activity";

    @Id
    private final String title;
    private final String content;
    private final String author;
    private final String lastUpdate;

    private final String activity;

    @JsonCreator
    public CArticle(@JsonProperty(TITLE) String title, @JsonProperty(CONTENT) String content, @JsonProperty(AUTHOR) String author,
                    @JsonProperty(LAST_UPDATE) String lastUpdate, @JsonProperty(ACTIVITY) String activity) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.lastUpdate = lastUpdate;
        this.activity = activity;
    }

    @JsonProperty(TITLE)
    public String getTitle() {
        return title;
    }

    @JsonProperty(CONTENT)
    public String getContent() {
        return content;
    }

    @JsonProperty(AUTHOR)
    public String getAuthor() {
        return author;
    }

    @JsonProperty(LAST_UPDATE)
    public String getLastUpdate() {
        return lastUpdate;
    }

    @JsonProperty(ACTIVITY)
    public String getActivity() {
        return activity;
    }
}
