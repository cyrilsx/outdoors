package org.nexu.outdoors.web.dao.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.nexu.outdoors.web.dao.util.MongoModel;

@MongoModel("image")
public class CImage extends CMedia {

    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String LINK_PREVIEW = "link_preview";

    private final int width;
    private final int height;

    private final String linkPreview;

    public CImage(@JsonProperty(ID) Long id, @JsonProperty(ACTIVITY) String activity, @JsonProperty(URL) String url, @JsonProperty(WIDTH) int width, @JsonProperty(HEIGHT) int height, @JsonProperty(LINK_PREVIEW)String linkPreview) {
        super(id, activity, url);
        this.width = width;
        this.height = height;
        this.linkPreview = linkPreview;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getLinkPreview() {
        return linkPreview;
    }
}
