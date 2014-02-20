package org.nexu.outdoors.web.dao.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.Id;

public abstract class CMedia {

    protected static final String ID = "_id";
    protected static final String URL = "url";
    protected static final String ACTIVITY = "activity";

    @Id
    private final Long id;

    private final String activity;
    private final String url;

    @JsonCreator
    protected CMedia(@JsonProperty(ID) Long id, @JsonProperty(ACTIVITY)String activity,@JsonProperty(URL) String url) {
        this.id = id;
        this.activity = activity;
        this.url = url;
    }

    @JsonProperty(ID)
    public Long getId() {
        return id;
    }

    @JsonProperty(ACTIVITY)
    public String getActivity() {
        return activity;
    }

    @JsonProperty(URL)
    public String getUrl() {
        return url;
    }
}
